package com.nsd.race.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nsd.race.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResorcesNotFoundException.class)
	public ResponseEntity<Map<Object, Object>> resorcesNotFoundException(ResorcesNotFoundException exp){
		
		String message = exp.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		Map<Object, Object> map = new HashMap<>();
		map.put("response", apiResponse);
		return new ResponseEntity<>(map , HttpStatusCode.valueOf(500));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<Object, Object>> exception(Exception exp){
		
		String message = exp.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		Map<Object, Object> map = new HashMap<>();
		map.put("response", apiResponse);
		return new ResponseEntity<>(map , HttpStatusCode.valueOf(500));
	}
}
