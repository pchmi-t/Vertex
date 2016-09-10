package com.fmi.javaee.vertex.task;

import com.fmi.javaee.vertex.rest.RestPackageRegistry;
import com.fmi.javaee.vertex.task.comment.CommentDAO;
import com.fmi.javaee.vertex.task.comment.CommentDAOImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TaskModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TaskService.class);
		bind(TaskDAO.class).to(TaskDAOImpl.class).in(Singleton.class);
		bind(CommentDAO.class).to(CommentDAOImpl.class).in(Singleton.class);
		
		RestPackageRegistry.registerPackage(TaskService.class.getPackage());
	}

}
