package com.example.springauthenticateendpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springauthenticateendpoint.model.Doctor;
import com.example.springauthenticateendpoint.model.User;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
