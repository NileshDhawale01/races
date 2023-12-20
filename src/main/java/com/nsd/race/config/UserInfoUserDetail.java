package com.nsd.race.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nsd.race.entities.UserInfo;

public class UserInfoUserDetail implements UserDetails {

	/**
	 * @author Nilesh Dhawale
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String password;

	private List<GrantedAuthority> authorities;

	public UserInfoUserDetail(UserInfo info) {
		this.name = info.getName();

		this.password = info.getPassword();

		this.authorities = Arrays.stream(info.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
