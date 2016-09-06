package com.fmi.javaee.vertex.guice;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;

import com.google.inject.Injector;
import com.squarespace.jersey2.guice.JerseyGuiceModule;

public class GuiceServiceLocatorGenerator implements ServiceLocatorGenerator {

	private static final String HK2_GENERATED_PREFIX = "__HK2_Generated_";

	private final Injector parentInjector;

	public GuiceServiceLocatorGenerator(final Injector parentInjector) {
		this.parentInjector = parentInjector;
	}

	@Override
	public ServiceLocator create(String name, ServiceLocator parent) {
		if (!name.startsWith(HK2_GENERATED_PREFIX)) {
			return null;
		}
		return parentInjector.createChildInjector(new JerseyGuiceModule(name)).getInstance(ServiceLocator.class);
	}

}
