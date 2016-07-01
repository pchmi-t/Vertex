package com.fmi.javaee.vertex;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class JacksonFeature implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		context.register(JacksonJaxbJsonProvider.class);
		return true;
	}

}
