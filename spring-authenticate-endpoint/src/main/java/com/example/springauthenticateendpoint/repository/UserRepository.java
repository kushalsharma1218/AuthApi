package com.example.springauthenticateendpoint.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springauthenticateendpoint.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmailId(String email);
	

}
