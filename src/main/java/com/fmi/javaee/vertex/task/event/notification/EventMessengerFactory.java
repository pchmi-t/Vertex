package com.fmi.javaee.vertex.task.event.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.task.event.EventType;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class EventMessengerFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventMessengerFactory.class);

	private final Injector injector;

	@Inject
	public EventMessengerFactory(Injector injector) {
		this.injector = injector;
	}

	public EventMessenger create(EventType eventType) {
		switch (eventType) {
		case ASSIGNMENT:
			return injector.getInstance(AssignmentMessenger.class);
		case STATUS:
			return injector.getInstance(StatusChangeMessenger.class);
		case PRIORITY:
			return injector.getInstance(PriorityChangeMessenger.class);
		default:
			LOGGER.warn("Cannot find a suitable messenger for event type " + eventType);
			return injector.getInstance(DefaultMessenger.class);
		}
	}

}
