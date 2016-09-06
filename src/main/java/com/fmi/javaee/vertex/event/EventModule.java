package com.fmi.javaee.vertex.event;

import com.fmi.javaee.vertex.rest.RestPackageRegistry;
import com.google.inject.AbstractModule;

public class EventModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EventDAO.class).to(EventDAOImpl.class);
		bind(EventService.class);

		RestPackageRegistry.registerPackage(EventService.class.getPackage());
	}

}
