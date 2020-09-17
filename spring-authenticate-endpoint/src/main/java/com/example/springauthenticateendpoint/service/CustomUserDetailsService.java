package com.example.springauthenticateendpoint.service;

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
		System.out.println("LoadByUsrName");
		User user  = userRepo.findByUsername(username);
		System.out.println("User "+user.getFirstName());
		CustomUserDetails customUser = null;
		customUser = new CustomUserDetails();
		customUser.setUser(user);
		System.out.println("User "+customUser.getUsername());
		return customUser;		
	}

}
