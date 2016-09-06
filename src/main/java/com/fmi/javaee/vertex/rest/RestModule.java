package com.fmi.javaee.vertex.rest;

import java.util.HashMap;
import java.util.Map;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class RestModule extends ServletModule {

	private static final String REST_BASE_PATH = "/api/*";

	@Override
	protected void configureServlets() {
		 bind(ServletContainer.class).in(Singleton.class);

         Map<String, String> initParams = new HashMap<String, String>();
         initParams.put(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfiguration.class.getName());
        
         serve(REST_BASE_PATH).with(ServletContainer.class, initParams);
	}
}
