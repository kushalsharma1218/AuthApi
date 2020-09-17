package com.example.springauthenticateendpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springauthenticateendpoint.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
