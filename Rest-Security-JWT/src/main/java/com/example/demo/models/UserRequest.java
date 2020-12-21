package com.example.demo.models;

public class UserRequest {

	private String username;
	private String password;

	public UserRequest() {
	}

	public UserRequest(String token, String message) {
		this.username = token;
		this.password = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
