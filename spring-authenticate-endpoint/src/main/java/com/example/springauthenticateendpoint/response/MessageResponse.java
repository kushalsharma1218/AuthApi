package com.example.springauthenticateendpoint.response;

import com.sun.el.stream.Optional;

public class MessageResponse {
	
	private String body;
	
	public MessageResponse(String string) 
	{
		this.body = string;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
