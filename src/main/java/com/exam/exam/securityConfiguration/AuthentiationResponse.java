package com.exam.exam.securityConfiguration;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

 @NoArgsConstructor
 @AllArgsConstructor
@Builder

public class AuthentiationResponse {
    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthentiationResponse [token=" + token + ", getToken()=" + getToken() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	 
}
