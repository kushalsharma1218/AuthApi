package com.example.springauthenticateendpoint.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springauthenticateendpoint.model.AuthRequest;
import com.example.springauthenticateendpoint.model.Doctor;
import com.example.springauthenticateendpoint.model.User;
import com.example.springauthenticateendpoint.repository.DoctorRepository;
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
	private DoctorRepository doctorRepo;
	
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
		  User user = userRepo.findByUsername(authRequest.getUsername());
	        if(user == null) 
	        {
	        	  return ResponseEntity.ok(new MessageResponse("Username doesn't exists"));
	        }
	        else {
	        	try {
	        	
	            authentication.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            return ResponseEntity.ok(new MessageResponse("invalid username or password"));
	        }
	        
	        String jwtToken = jwtUtil.generateToken(authRequest.getUsername());
	       
	        
	        return ResponseEntity.ok(new JwtResponse(jwtToken,user.getId(),user.getFirstName(),user.getLastName(),authRequest.getUsername(), user.getEmailId(),user.getRole()));
	        }
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
		
		@PostMapping("/addDoctor")
		public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) 
		{
			if(userRepo.existsByUsername(doctor.getUsername())) 
			{
				return ResponseEntity
						.ok()
						.body(new MessageResponse("Error: Username is already taken!"));
			}
			else if(userRepo.existsByEmailId(doctor.getEmailId())) 
			{
				return ResponseEntity
						.ok()
						.body(new MessageResponse("Error: Email is already in use!"));
			}
			else {
			String pass = doctor.getPassword();
			String encryptPass = passwordEncoder.encode(pass);
			doctor.setPassword(encryptPass);
	
			User user = new User();
			user.setEmailId(doctor.getEmailId());
			user.setUsername(doctor.getUsername());
			user.setFirstName(doctor.getFirstName());
			user.setGovermentId(doctor.getGovermentId());
			user.setLastName(doctor.getLastName());
			user.setPassword(doctor.getPassword());
			user.setPhone(doctor.getPhone());
			user.setRole(doctor.getRole());
			userRepo.save(user);
			
			doctor.setId(user.getId());
			doctorRepo.save(doctor);
			return ResponseEntity.ok(new MessageResponse("Doctor registered successfully!"));
			}
		}
		
		@CrossOrigin(origins = "*")
		@PreAuthorize("hasRole('ADMIN')")
		@GetMapping("/admin/getAllUsers")
		public List<User> findAll()
		{
			return userRepo.findAll();
		}
		
//		@RequestMapping(value="/logout")
//		void logoutPage() {   
//	        if (authentication != null){      
//	           new SecurityContextLogoutHandler().logout(request, response, authentication);  
//	        }  
//	         return "";  
//	     }
//		
		
		
}
