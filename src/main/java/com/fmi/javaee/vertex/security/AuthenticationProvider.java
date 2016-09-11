package com.fmi.javaee.vertex.security;

import com.fmi.javaee.vertex.user.UserDAO;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Injector;

public class AuthenticationProvider {

	private final UserDAO userDAO;

	private AuthenticationProvider(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	private static AuthenticationProvider instance;

	public static AuthenticationProvider getInstance() {
		if (instance == null) {
			throw new IllegalStateException("Authentication module not initialized!");
		}
		return instance;
	}

	public UserAuthentication authenticate(String email, char[] password) throws AuthenticationException {
		UserEntity user = userDAO.getUser(email, password);
		 if (user != null) {
			 return new UserAuthentication(user.getUsername(), user.getIsGod());
		 }
		throw new AuthenticationException("Cannot authenticate user with email: " + email);
	}

	public static void initialize(Injector guiceInjector) {
		instance = new AuthenticationProvider(guiceInjector.getInstance(UserDAO.class));
	}

}
