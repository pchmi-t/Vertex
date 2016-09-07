package com.fmi.javaee.vertex.guice;

import com.fmi.javaee.vertex.mail.MailModule;
import com.fmi.javaee.vertex.project.ProjectModule;
import com.fmi.javaee.vertex.rest.RestModule;
import com.fmi.javaee.vertex.security.SecurityModule;
import com.fmi.javaee.vertex.task.TaskModule;
import com.fmi.javaee.vertex.task.event.EventModule;
import com.fmi.javaee.vertex.user.UserModule;
import com.google.inject.AbstractModule;

public class VertexModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new UserModule());
		install(new TaskModule());
		install(new ProjectModule());
		install(new EventModule());
		install(new SecurityModule());
		install(new PersistModule());
		install(new RestModule());
		install(new MailModule());
	}


}
