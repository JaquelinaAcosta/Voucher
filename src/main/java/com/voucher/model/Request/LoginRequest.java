package com.voucher.model.Request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
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
	
	
/*
	@NotBlank
	private String username;
public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
*/	
	
	
	
}
