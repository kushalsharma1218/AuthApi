package com.example.springauthenticateendpoint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//url based security
@RestController
@RequestMapping("/rest/authentication")
public class ApplicationController {

	
	@GetMapping("/process")
	public String process() 
	{
		return "Processing ...."; 
	}
}
