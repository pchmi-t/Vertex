package com.fmi.javaee.vertex.security;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

	private static final String VERTEX_LOGIN_CONTEXT_NAME = "vertex";

	private static final String PASSWORD_PARAM = "j_password";

	private static final String USERNAME_PARAM = "j_username";

	private final static Configuration JAAS_CONFIG = new JaasConfiguration();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			LOG.info("Initializing the login context...");

			String username = request.getParameter(USERNAME_PARAM);
			String password = request.getParameter(PASSWORD_PARAM);

			LoginCallbackHandler callbackHandler = new LoginCallbackHandler(username, password.toCharArray());
			LoginContext lc = new LoginContext(VERTEX_LOGIN_CONTEXT_NAME, new Subject(), callbackHandler, JAAS_CONFIG);
			lc.login();
		} catch (LoginException e) {
			LOG.error("Could not perform authentication", e);
			throw new ServletException(e);
		}
	}

}
