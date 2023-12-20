package com.nsd.race.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nsd.race.config.UserInfoUserDetail;
import com.nsd.race.entities.UserInfo;
import com.nsd.race.repositories.UserInfoRepo;

@Component
public class UserInfoUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserInfoRepo infoRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = infoRepo.findByName(username);
		return userInfo.map(UserInfoUserDetail::new).orElseThrow(()-> new UsernameNotFoundException(username));
	}

	
}
