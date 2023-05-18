package com.exam.exam.securityConfiguration;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

 @Builder
 @NoArgsConstructor
 @AllArgsConstructor


public class AuthenticateRequest {
 private String username;
 public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
private String email;
 private String password;
 
}
