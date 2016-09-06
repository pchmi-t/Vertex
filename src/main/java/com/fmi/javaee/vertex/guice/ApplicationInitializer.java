package com.fmi.javaee.vertex.guice;

import javax.servlet.ServletContextEvent;

import com.fmi.javaee.vertex.security.AuthenticationProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.GuiceServletContextListener;
import com.squarespace.jersey2.guice.JerseyGuiceUtils;

public class ApplicationInitializer extends GuiceServletContextListener {

	private static final String INJECTOR_NAME = Injector.class.getName();

	private PersistService persistenceService;
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new VertexModule());
	}

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		super.contextDestroyed(context);
		if (persistenceService != null) {
			persistenceService.stop();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		super.contextInitialized(context);

		// The injector has been put into the ServletContext by the
		// GuiceServletContextListener superclass
		Injector injector = (Injector) context.getServletContext().getAttribute(INJECTOR_NAME);

		initializePersistence(injector);
		AuthenticationProvider.initialize(injector);
		JerseyGuiceUtils.install(new GuiceServiceLocatorGenerator(injector));
	}

	private void initializePersistence(Injector injector) {
		persistenceService = injector.getInstance(PersistService.class);
		persistenceService.start();
	}

}
