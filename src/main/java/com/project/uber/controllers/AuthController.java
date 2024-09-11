package com.project.uber.controllers;

import com.project.uber.dto.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import com.project.uber.services.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RestController
@RequestMapping(path="/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping(path="/signup")
	public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDto)
	{
		return new ResponseEntity<>(authService.signup(signUpDto), HttpStatus.CREATED);
	}

	@PostMapping(path="/onboardNewDriver/{userId}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<DriverDTO>onboardNewDriver(@PathVariable Long userId,@RequestBody OnboardDriverDTO onboardDriverDto)
	{
		return new ResponseEntity<>(authService.onboardNewDriver(userId,onboardDriverDto.getVehicleId()),HttpStatus.CREATED);
	}

	@PostMapping(path="/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto, HttpServletRequest request, HttpServletResponse response)
	{
		LoginResponseDTO loginResponseDTO = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
		Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		return ResponseEntity.ok(loginResponseDTO);
	}

	@PostMapping("/refresh")
	public ResponseEntity<LoginResponseDTO> refreshAccessToken(HttpServletRequest request)
	{

		String refreshToken = Arrays.stream(request.getCookies())
				.filter(cookie -> cookie.getName().equals("refreshToken"))
				.findFirst()
				.map(cookie -> cookie.getValue())
				.orElseThrow(()->new AuthenticationServiceException("Refresh token not found"));

		LoginResponseDTO loginResponse = authService.refreshToken(refreshToken);

		return ResponseEntity.ok(loginResponse);

	}

	@GetMapping("/healthcheck")
	public ResponseEntity<String> healthcheck()
	{
		return ResponseEntity.ok("uber app is up");
	}
}
