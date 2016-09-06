package com.fmi.javaee.vertex.task;

import com.fmi.javaee.vertex.rest.RestPackageRegistry;
import com.google.inject.AbstractModule;

public class TaskModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TaskService.class);
		bind(TaskDAO.class).to(TaskDAOImpl.class);
		
		RestPackageRegistry.registerPackage(TaskService.class.getPackage());
	}

}
