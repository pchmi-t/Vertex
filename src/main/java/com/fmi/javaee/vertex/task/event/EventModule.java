package com.fmi.javaee.vertex.task.event;

import com.fmi.javaee.vertex.rest.RestPackageRegistry;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionDAO;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionDAOImpl;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class EventModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EventService.class);
		bind(EventBus.class).asEagerSingleton();
		bind(EventListener.class).asEagerSingleton();
		bind(EventDAO.class).to(EventDAOImpl.class).in(Singleton.class);

		bind(SubscriptionDAO.class).to(SubscriptionDAOImpl.class).in(Singleton.class);

		RestPackageRegistry.registerPackage(EventService.class.getPackage());
	}

}
