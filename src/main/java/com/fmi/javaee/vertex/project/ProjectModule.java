package com.fmi.javaee.vertex.project;

import com.fmi.javaee.vertex.rest.RestPackageRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ProjectModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ProjectService.class);
		bind(ProjectDAO.class).to(ProjectDAOImpl.class).in(Singleton.class);

		RestPackageRegistry.registerPackage(ProjectService.class.getPackage());
	}

}
