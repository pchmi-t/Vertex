package com.fmi.javaee.vertex.event.compose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.task.Component;
import com.fmi.javaee.vertex.task.TaskEntity;

public class PriorityEvent implements ComponentEvent {

	private Component component = Component.PRIORITY;
	
	private static final Logger LOG = LoggerFactory.getLogger(PriorityEvent.class);

	@Override
	public void composeEvent(TaskEntity task) {
//		EventEntity event = new EventEntity();
//		event.setRefTask(task);
//		event.setCreationTime(new Date(System.currentTimeMillis()));
//		String description = MessageFormat.format("Property {0} in task {1} has changed to {2} at {3}.", 
//				component, task.getTaskId(), task.getDefinition(), event.getCreationTime());
//		event.setDescription(description);
//		event.setEventComponent(component);
//		EventEntity newEvent = factory.getEventDAO().createEvent(event);
//		if (newEvent == null ){
//			LOG.info("An error occured while creating event for component: " + component);
//		} else {
//			LOG.info(description);
//		}
	}

}
