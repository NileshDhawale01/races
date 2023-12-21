package com.nsd.race.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nsd.race.entities.UserInfo;
import com.nsd.race.repositories.UserInfoRepo;
import com.nsd.race.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserInfoRepo infoRepo;
	
	@Override
	public UserInfo saveUser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		log.info("save user...");
		return infoRepo.save(userInfo);
	}

}
