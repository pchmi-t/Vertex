package com.fmi.javaee.vertex.security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VertexLoginModule implements LoginModule {

	private static final Logger LOG = LoggerFactory.getLogger(VertexLoginModule.class);

	private static final String USERNAME_CALLBACK_PROMPT = "username";
	private static final String PASSWORD_CALLBACK_PROMPT = "password";

	private CallbackHandler handler;
	private NameCallback nameCallback;
	private PasswordCallback passwordCallback;
	private VertexUserPrincipal userPrincipal;
	private Callback[] callbacks;
	private Subject subject;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.nameCallback = new NameCallback(USERNAME_CALLBACK_PROMPT);
		this.passwordCallback = new PasswordCallback(PASSWORD_CALLBACK_PROMPT, false);
		this.callbacks = new Callback[] { nameCallback, passwordCallback };
		this.handler = callbackHandler;
		this.subject = subject;

	}

	@Override
	public boolean login() throws LoginException {
		LOG.info("Will try to log user...");
		try {
			handler.handle(callbacks);
			String username = nameCallback.getName();
			char[] password = passwordCallback.getPassword();
			if (checkCredentials(username, password)) {
				this.userPrincipal = new VertexUserPrincipal(username);
				return true;
			}
			throw new LoginException("Authentication failed");
		} catch (IOException | UnsupportedCallbackException e) {
			throw new LoginException(e.getMessage());
		}
	}

	private boolean checkCredentials(String username, char[] password) {
		LOG.info("Checking the credentials of user [{}]", username);
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		subject.getPrincipals().add(userPrincipal);
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		return true;
	}

}
