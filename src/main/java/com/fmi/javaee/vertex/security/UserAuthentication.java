package com.fmi.javaee.vertex.security;

public class UserAuthentication {

	private final String username;
	private final boolean isGod;

	public UserAuthentication(String username, boolean isGod) {
		this.username = username;
		this.isGod = isGod;
	}

	public String getUsername() {
		return username;
	}

	public boolean isGod() {
		return isGod;
	}

}
