package com.fmi.javaee.vertex.user;

import com.fmi.javaee.vertex.rest.RestPackageRegistry;
import com.google.inject.AbstractModule;

public class UserModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserDAO.class).to(UserDAOImpl.class);
		
		bind(UserService.class);
		bind(UserRegisterService.class);
		
		RestPackageRegistry.registerPackage(UserService.class.getPackage());
		RestPackageRegistry.registerPackage(UserRegisterService.class.getPackage());
	}

}
