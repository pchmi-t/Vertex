package com.fmi.javaee.vertex.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.UserEntity;
import com.fmi.javaee.vertex.user.data.UserData;

public class VertexLoginModule implements LoginModule {

	private static final Logger LOG = LoggerFactory.getLogger(VertexLoginModule.class);

	private static final String USER_OR_EMAIL_CALLBACK_PROMPT = "email";
	private static final String PASSWORD_CALLBACK_PROMPT = "password";

	private static final String VERTEX_USER_DEFAULT_ROLE = "VertexUser";

	private CallbackHandler handler;
	private NameCallback emailCallback;
	private PasswordCallback passwordCallback;
	private Callback[] callbacks;

	private Subject subject;
	private UserPrincipal userPrincipal;
	private Set<RolePrincipal> userRoles;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.emailCallback = new NameCallback(USER_OR_EMAIL_CALLBACK_PROMPT);
		this.passwordCallback = new PasswordCallback(PASSWORD_CALLBACK_PROMPT, false);
		this.callbacks = new Callback[] { emailCallback, passwordCallback };
		this.userRoles = new HashSet<>();
		this.handler = callbackHandler;
		this.subject = subject;
	}

	@Override
	public boolean login() throws LoginException {
		LOG.info("Will try to log user...");
		try {
			handler.handle(callbacks);
			String usernameOrEmail = emailCallback.getName();
			char[] password = passwordCallback.getPassword();

			if (login(usernameOrEmail, password)) {
				return true;
			}
			throw new LoginException("Authentication failed");
		} catch (IOException | UnsupportedCallbackException e) {
			throw new LoginException(e.getMessage());
		}
	}

	private boolean login(String email, char[] password) {
		LOG.info("Checking the credentials of user [{}]", email);
		UserData userDAO = Factory.getInstance().getUserData();
		UserEntity user = userDAO.getUser(email, password);
		if (user == null) {
			return false;
		}
		this.userPrincipal = new UserPrincipal(user.getUsername());
		this.userRoles.add(new RolePrincipal(VERTEX_USER_DEFAULT_ROLE));
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		subject.getPrincipals().add(userPrincipal);
		for (RolePrincipal role : userRoles) {
			subject.getPrincipals().add(role);
		}
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		for (RolePrincipal role : userRoles) {
			subject.getPrincipals().remove(role);
		}
		return true;
	}

}
