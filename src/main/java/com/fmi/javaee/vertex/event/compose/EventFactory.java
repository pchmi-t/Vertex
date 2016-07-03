package com.fmi.javaee.vertex.event.compose;

import com.fmi.javaee.vertex.task.monitoring.Component;

public class EventFactory {

	private EventFactory() {
	}

	public static EventFactory getInstance() {
		return new EventFactory();
	}

	public ComponentEvent getEventByComponent(Component component) {
		switch (component) {
		case STATUS:
			return new StatusEvent();
		case DEFINITION:
			return new DefinitionEvent();
		case PRIORITY:
			return new PriorityEvent();
		case COMMENT:
			return new CommentEvent();
		default:
			return null;
		}
	}
}
