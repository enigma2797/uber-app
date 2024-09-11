package com.project.uber.services;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.LoginResponseDTO;
import com.project.uber.dto.SignUpDTO;
import com.project.uber.dto.UserDTO;

public interface AuthService {
	
	public LoginResponseDTO login(String email, String password);
  
	public UserDTO signup(SignUpDTO signUpDTO);
	
	public DriverDTO onboardNewDriver(Long userId, String vehicleId);

	LoginResponseDTO refreshToken(String refreshToken);
}
