package com.example.springauthenticateendpoint.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
public class User {

	@javax.persistence.Id
	@GeneratedValue
	private int Id;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String username;
	private String phone;
	private String password;
	private String govermentId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//Separate table and connect user and role
	@JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name ="role_id"))
	@Column(name = "roles")
	private Set<Role> roles;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getGovermentId() {
		return govermentId;
	}
	public void setGovermentId(String govermentId) {
		this.govermentId = govermentId;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	
}
