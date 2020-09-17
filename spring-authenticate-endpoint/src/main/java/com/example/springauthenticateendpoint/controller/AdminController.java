package com.example.springauthenticateendpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.springauthenticateendpoint.model.User;
import com.example.springauthenticateendpoint.repository.UserRepository;

@RestController
@RequestMapping("/secure/auth")
public class AdminController {

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/admin/newpatient")
	public String addUserByAdmin(@RequestBody User patient) 
	{
		String pass = patient.getPassword();
		String encryptPass = passwordEncoder.encode(pass);
		patient.setPassword(encryptPass);
		userRepo.save(patient);
		
		return "Patient added successfully";
	}	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/admin/all")
	public String securedHello() {
		return "Secured Hello";
	}
}
