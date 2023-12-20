package com.nsd.race.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsd.race.dto.AuthReq;
import com.nsd.race.entities.UserInfo;
import com.nsd.race.services.JwtService;
import com.nsd.race.services.UserService;

@RestController
@RequestMapping("/basic")
public class BasicController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<Map<Object, Object>> getBasicData() {
		Map<Object, Object> map = new HashMap<>();
		map.put("data", "this is the basic data");
		map.put("success", true);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Map<Object, Object>> saveUser(@RequestBody UserInfo info){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("data", service.saveUser(info));
		map.put("success", true);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Map<Object, Object>> authenticateAndGetToken(@RequestBody AuthReq authReq) {
		Authentication manager = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authReq.getName(), authReq.getPassword()));
		if (manager.isAuthenticated()) {
			Map<Object, Object> map = new HashMap<>();
			map.put("data", jwtService.generateToken(authReq.getName()));
			map.put("success", true);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new UsernameNotFoundException(authReq.getName());
		}
	}
}
