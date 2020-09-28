package com.example.springauthenticateendpoint.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springauthenticateendpoint.model.AuthRequest;
import com.example.springauthenticateendpoint.model.Role;
import com.example.springauthenticateendpoint.model.User;
import com.example.springauthenticateendpoint.repository.UserRepository;
import com.example.springauthenticateendpoint.response.JwtResponse;
import com.example.springauthenticateendpoint.response.MessageResponse;
import com.example.springauthenticateendpoint.util.JwtUtil;


@RestController
public class ApplicationController {

	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authentication;


	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/process")
	public String process() 
	{
		return "Processing ...."; 
	}
	
	  @PostMapping("/authenticate")
	    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	        	
	            authentication.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            return ResponseEntity.ok(new MessageResponse("invalid username or password"));
	        }
	        
	        User user = userRepo.findByUsername(authRequest.getUsername());
	        String jwtToken = jwtUtil.generateToken(authRequest.getUsername());
	        
	        return ResponseEntity.ok(new JwtResponse(jwtToken,authRequest.getUsername(), user.getEmailId()));
	    }
	  
		@PostMapping("/register")
		public ResponseEntity<?> addNewPatient(@RequestBody User patient) 
		{
			if(userRepo.existsByUsername(patient.getUsername())) 
			{
				return ResponseEntity
						.ok()
						.body(new MessageResponse("Error: Username is already taken!"));
			}
			else if(userRepo.existsByEmailId(patient.getEmailId())) 
			{
				return ResponseEntity
						.ok()
						.body(new MessageResponse("Error: Email is already in use!"));
			}
			else {
			String pass = patient.getPassword();
			String encryptPass = passwordEncoder.encode(pass);
			patient.setPassword(encryptPass);
			userRepo.save(patient);
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
			}
		}
		
		
		
}
