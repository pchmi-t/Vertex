package com.fmi.javaee.vertex.event.compose;

import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.event.EventBean;
import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.task.monitoring.Component;

public class StatusEvent implements ComponentEvent {


	private Factory factory = Factory.getInstance();
	
	private Component component = Component.STATUS;
	
	private static final Logger LOG = LoggerFactory.getLogger(StatusEvent.class);

	@Override
	public void composeEvent(TaskBean task) {
		EventBean event = new EventBean();
		event.setRefTask(task);
		event.setCreationTime(new Date(System.currentTimeMillis()));
		String description = MessageFormat.format("Property {0} in task {1} has changed to {2} at {3}.", 
				component, task.getTaskId(), task.getDefinition(), event.getCreationTime());
		event.setDescription(description);
		EventBean newEvent = factory.getEventData().createEvent(event);
		if (newEvent == null ){
			LOG.equals("An error occured while creating event for component: " + component);
		} else {
			LOG.info(description);
		}
	}

}
