package com.example.springauthenticateendpoint.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springauthenticateendpoint.model.User;
import com.example.springauthenticateendpoint.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(userRepo.findByUsername(username) == null) 
		{
			throw new  UsernameNotFoundException("User with username "+username+" doesn't exists");
		}
		User user = userRepo.findByUsername(username);
				
		CustomUserDetails customUser = null;
		customUser = new CustomUserDetails();
		customUser.setUser(user);
	
		return customUser;
		
	}

}
