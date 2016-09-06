package com.fmi.javaee.vertex.guice;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class PersistModule extends AbstractModule {

	private static final String PERSISTENCE_UNIT = "VertexDatabase";

	@Override
	protected void configure() {
		install(new JpaPersistModule(PERSISTENCE_UNIT));
		
	}

}
