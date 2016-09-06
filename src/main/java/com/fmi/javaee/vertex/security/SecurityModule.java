package com.fmi.javaee.vertex.security;

import com.google.inject.servlet.ServletModule;

public class SecurityModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/logout").with(LogoutServlet.class);
		
	}

}
