package com.fmi.javaee.vertex.project;

import com.fmi.javaee.vertex.rest.RestPackageRegistry;
import com.google.inject.AbstractModule;

public class ProjectModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ProjectService.class);
		bind(ProjectDAO.class).to(ProjectDAOImpl.class);

		RestPackageRegistry.registerPackage(ProjectService.class.getPackage());
	}

}
