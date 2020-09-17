package com.example.springauthenticateendpoint.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springauthenticateendpoint.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class CustomUserDetails implements UserDetails {


	private static final long serialVersionUID = 1L;
	private User user;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("Granted Authority");
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole())).collect(Collectors.toList()); 
	}

	@Override
	public String getPassword() {
		System.out.println("GetPassword called");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		System.out.println("GteUSerName called");
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		System.out.println("isAccountNonExpired");
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}