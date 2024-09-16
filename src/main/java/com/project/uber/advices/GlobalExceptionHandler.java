package com.project.uber.advices;

import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.exceptions.RuntimeConflictException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		ApiError error = ApiError.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
		return buildErrorResponseEntity(error);
	}

	@ExceptionHandler(RuntimeConflictException.class)
	public ResponseEntity<ApiResponse<?>> handleRuntimeConflictException(RuntimeConflictException ex)
	{
		ApiError error = ApiError.builder().status(HttpStatus.CONFLICT).message(ex.getMessage()).build();
		return buildErrorResponseEntity(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleOtherException(Exception ex)
	{
		ApiError error = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();
		return buildErrorResponseEntity(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleInputValidationException(MethodArgumentNotValidException ex)
	{
		List<String> errorList = ex
		.getBindingResult()
		.getAllErrors()
		.stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
		ApiError error = ApiError.builder().status(HttpStatus.BAD_REQUEST).message("input validation failed").subErrors(errorList).build();
		return buildErrorResponseEntity(error);
	}
	
	public ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError error)
	{
		return new ResponseEntity<ApiResponse<?>>(new ApiResponse<>(error),error.getStatus());
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException ex)
	{
		ApiError error = ApiError.builder().status(HttpStatus.UNAUTHORIZED).message(ex.getMessage()).build();

		return buildErrorResponseEntity(error);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException ex)
	{
		ApiError error = ApiError.builder().status(HttpStatus.UNAUTHORIZED).message(ex.getMessage()).build();

		return buildErrorResponseEntity(error);

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex)
	{
		ApiError error = ApiError.builder().status(HttpStatus.FORBIDDEN).message(ex.getMessage()).build();

		return buildErrorResponseEntity(error);
	}

}
