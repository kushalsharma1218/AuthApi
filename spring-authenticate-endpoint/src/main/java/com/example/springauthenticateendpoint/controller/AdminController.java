package com.example.springauthenticateendpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.springauthenticateendpoint.model.User;
import com.example.springauthenticateendpoint.repository.UserRepository;


//HospitalAdminWork

@RestController
@RequestMapping("/v1/auth")
public class AdminController {

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/newuser")
	public String addUserByAdmin(@RequestBody User patient) 
	{
		String pass = patient.getPassword();
		String encryptPass = passwordEncoder.encode(pass);
		patient.setPassword(encryptPass);
		userRepo.save(patient);
		
		return "Patient added successfully";
	}	
	


}
