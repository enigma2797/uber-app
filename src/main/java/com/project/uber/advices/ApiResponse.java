package com.project.uber.advices;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class ApiResponse<T> {
	
	private T data;
	private ApiError errors;
	//@JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
	private LocalDateTime timestamp;
	
	public ApiResponse()
	{
		timestamp = LocalDateTime.now();
	}

	public ApiResponse(T data) {
		this();
		this.data = data;
	}

	public ApiResponse(ApiError errors) {
		this();
		this.errors = errors;
	}

	
	
	

}
