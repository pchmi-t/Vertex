package com.fmi.javaee.vertex.rest;

import java.util.Arrays;

import javax.servlet.ServletContext;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class JerseyConfiguration extends ResourceConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JerseyConfiguration.class);
	
	private static final String INJECTOR_NAME = Injector.class.getName();
	
	/**
	 * The constructor initializes the ServiceLocator, which contains services which have to be injected with Guice.
	 * It tells the initialized ServiceLocator about the specific Guice Injector from which services will be injected.
	 * Any Guice Injector added with the bridgeGuiceInjector method will be searched for services that HK2 cannot otherwise find.
	 * 
	 * <p><b>HK2</b> is the dependency injection framework used in Jersey 2.x</p>
	 * 
	 * @param serviceLocator
	 * @param servletContext
	 */
	
	@Inject
	public JerseyConfiguration(ServiceLocator serviceLocator, ServletContext servletContext) {
		String[] restPackages = RestPackageRegistry.getRestPackages();
		LOGGER.debug("Registering packages [{}]", Arrays.asList(restPackages));
		packages(restPackages);

		
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		
		Injector injector = (Injector) servletContext.getAttribute(INJECTOR_NAME);
		LOGGER.debug("Obtained injector [{}]", injector);
		guiceBridge.bridgeGuiceInjector(injector);
	}

}
