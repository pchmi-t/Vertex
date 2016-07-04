package com.fmi.javaee.vertex.event.compose;

import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.event.EventEntity;
import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.task.monitoring.Component;

public class DefinitionEvent implements ComponentEvent {

	private Factory factory = Factory.getInstance();
	
	private Component component = Component.DEFINITION;
	
	private static final Logger LOG = LoggerFactory.getLogger(DefinitionEvent.class);

	@Override
	public void composeEvent(TaskBean task) {
		EventEntity event = new EventEntity();
		event.setRefTask(task);
		event.setCreationTime(new Date(System.currentTimeMillis()));
		String description = MessageFormat.format("Property {0} in task {1} has changed to {2} at {3}.", 
				component, task.getTaskId(), task.getDefinition(), event.getCreationTime());
		event.setDescription(description);
		event.setEventComponent(component);
		EventEntity newEvent = factory.getEventDAO().createEvent(event);
		if (newEvent == null ){
			LOG.info("An error occured while creating event for component: " + component);
		} else {
			LOG.info(description);
		}
	}

}
