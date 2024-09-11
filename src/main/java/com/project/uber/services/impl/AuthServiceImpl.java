package com.project.uber.services.impl;

import java.util.Set;

import com.project.spring_mvc.exceptions.ResourceNotFoundException;
import com.project.uber.dto.*;
import com.project.uber.entities.Driver;
import com.project.uber.security.JwtService;
import com.project.uber.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.uber.entities.User;
import com.project.uber.entities.enums.Role;
import com.project.uber.exceptions.RuntimeConflictException;
import com.project.uber.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
 
	private final ModelMapper mapper;
	private final UserRepository userRepo;
	private final RiderService riderService;
	private final WalletService walletService;
	private final DriverService driverService;
	private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
	private  final JwtService jwtService;
	private final UserService userService;
	@Override
	public LoginResponseDTO login(String email, String password) {
		// TODO Auto-generated method stub
      Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
	  User user = (User) authentication.getPrincipal();
	  String accessToken = jwtService.generateAccessToken(user);
	  String refreshToken = jwtService.generateRefreshToken(user);

        return LoginResponseDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
		
	}

	@Override
	@Transactional
	public UserDTO signup(SignUpDTO signUpDTO) {
		// TODO Auto-generated method stub
	   User user = userRepo.findByEmail(signUpDTO.getEmail());
	   if(user!=null)
		   throw new RuntimeConflictException("Can't signup. User already exists with email:"+signUpDTO.getEmail());
	   User mappedUser = mapper.map(signUpDTO,User.class);
	   mappedUser.setRoles(Set.of(Role.RIDER));
	   mappedUser.setPassword(encoder.encode(mappedUser.getPassword()));
	   User savedUser = userRepo.save(mappedUser);
	   riderService.createRider(mappedUser);
	   walletService.createNewWallet(mappedUser);
	   return mapper.map(mappedUser,UserDTO.class);
	}

	@Override
	public DriverDTO onboardNewDriver(Long userId,String vehicleId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user doesn't exist with id:"+userId));
		if(user.getRoles().contains(Role.DRIVER))
			throw new RuntimeConflictException("user with id:"+userId+" is already a driver");
		Driver newDriver = Driver.builder().
				user(user)
				.vehicleId(vehicleId)
				.rating(0.0)
				.available(true)
				.build();
		user.getRoles().add(Role.DRIVER);
        userRepo.save(user);
		Driver savedDriver = driverService.createNewDriver(newDriver);
		return mapper.map(savedDriver,DriverDTO.class);
	}

	@Override
	public LoginResponseDTO refreshToken(String refreshToken) {

		Long id = jwtService.getUserFromToken(refreshToken);
		User user = userService.getUserById(id);

		String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDTO(id,accessToken,refreshToken);
	}


}
