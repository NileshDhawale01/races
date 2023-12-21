package com.nsd.race.controllers;

import static com.nsd.race.contants.ApiContants.AUTHENTICATE;
import static com.nsd.race.contants.ApiContants.BASIC;
import static com.nsd.race.contants.ApiContants.SAVE;
import static com.nsd.race.contants.NameContants.DATA;
import static com.nsd.race.contants.NameContants.SUCCESS;

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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(BASIC)
@Slf4j
public class BasicController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<Map<Object, Object>> getBasicData() {
		log.info("starting getBasicData()");
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, "this is the basic data");
		map.put(SUCCESS, true);
		log.info("ending getBasicData()");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping(SAVE)
	public ResponseEntity<Map<Object, Object>> saveUser(@RequestBody UserInfo info){
		
		log.info("starting of saveUser()");
		Map<Object, Object> map = new HashMap<>();
		map.put(DATA, service.saveUser(info));
		map.put(SUCCESS, true);
		log.info("ending of saveUser()");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

	@PostMapping(AUTHENTICATE)
	public ResponseEntity<Map<Object, Object>> authenticateAndGetToken(@RequestBody AuthReq authReq) {
		
		log.info("starting of authenticateAndGetToken()");
		Authentication manager = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authReq.getName(), authReq.getPassword()));
		if (manager.isAuthenticated()) {
			Map<Object, Object> map = new HashMap<>();
			map.put(DATA, jwtService.generateToken(authReq.getName()));
			map.put(SUCCESS, true);
			log.info("returning of token");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			log.info("something wrong in authenticateAndGetToken()");
			throw new UsernameNotFoundException(authReq.getName());
		}
	}
}
