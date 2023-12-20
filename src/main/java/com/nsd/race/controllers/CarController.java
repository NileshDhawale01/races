package com.nsd.race.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<Object, Object>> getAllCars(){
		Map<Object, Object> map = new HashMap<>();
		map.put("data", "fine It working");
		map.put("success", true);
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
}
