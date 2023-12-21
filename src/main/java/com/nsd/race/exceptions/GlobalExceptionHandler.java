package com.nsd.race.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nsd.race.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(ResorcesNotFoundException.class)
	public ResponseEntity<Map<Object, Object>> resorcesNotFoundException(ResorcesNotFoundException exp){
		
		String message = exp.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		Map<Object, Object> map = new HashMap<>();
		map.put("response", apiResponse);
		log.info("returning from resorcesNotFoundException()");
		return new ResponseEntity<>(map , HttpStatusCode.valueOf(500));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<Object, Object>> exception(Exception exp){
		
		String message = exp.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		Map<Object, Object> map = new HashMap<>();
		map.put("response", apiResponse);
		log.info("returning from exception()");
		return new ResponseEntity<>(map , HttpStatusCode.valueOf(500));
	}
}
