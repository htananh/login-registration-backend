package com.example.security.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;


import lombok.ToString;


@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
}
