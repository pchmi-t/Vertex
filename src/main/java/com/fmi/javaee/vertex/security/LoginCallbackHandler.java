package com.fmi.javaee.vertex.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class LoginCallbackHandler implements CallbackHandler {

	private final String username;
	private final char[] password;

	public LoginCallbackHandler(String username, char[] password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (Callback callback : callbacks) {
			if (callback instanceof NameCallback) {
				handle((NameCallback) callback);
				return;
			} else if (callback instanceof PasswordCallback) {
				handle((PasswordCallback) callback);
				return;
			}
			throw new UnsupportedCallbackException(callback);
		}
	}

	private void handle(PasswordCallback psCallback) {
		psCallback.setPassword(password);
	}

	private void handle(NameCallback nameCallback) {
		nameCallback.setName(username);
	}

}
