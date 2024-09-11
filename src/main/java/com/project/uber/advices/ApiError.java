package com.project.uber.advices;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiError {
	
	private HttpStatus status;
	private String message;
	private List<String>subErrors;

}
