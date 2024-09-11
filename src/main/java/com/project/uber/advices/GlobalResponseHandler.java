package com.project.uber.advices;

import java.net.URL;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// TODO Auto-generated method stub
		String path = request.getURI().toString();
		if(path.contains("v3/api-docs"))
			return body;
		if(body instanceof ApiResponse<?>)
			return body;
		
		else
			return new ApiResponse<>(body);
		
	}

}
