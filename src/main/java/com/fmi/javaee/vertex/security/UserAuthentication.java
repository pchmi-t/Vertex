package com.fmi.javaee.vertex.security;

public class UserAuthentication {

	private final String username;

	public UserAuthentication(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
