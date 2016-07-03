package com.fmi.javaee.vertex.task.monitoring;

import java.util.Observable;
import java.util.Observer;

import com.fmi.javaee.vertex.event.compose.ComponentEvent;
import com.fmi.javaee.vertex.event.compose.EventFactory;
import com.fmi.javaee.vertex.task.TaskBean;

public class TaskMonitoring implements Observer {

	private TaskBean changedTask;

	@Override
	public void update(Observable o, Object component) {
		changedTask = (TaskBean) o;
		if (changedTask.getTaskId() != null) {
			Component componentChanged = (Component) component;
			ComponentEvent event;
			switch (componentChanged) {
			case STATUS:
				event = EventFactory.getInstance().getEventByComponent(Component.STATUS);
				break;
			case DEFINITION:
				event = EventFactory.getInstance().getEventByComponent(Component.DEFINITION);
				break;
			case PRIORITY:
				event = EventFactory.getInstance().getEventByComponent(Component.PRIORITY);
				break;
			case COMMENT:
				event = EventFactory.getInstance().getEventByComponent(Component.COMMENT);
				break;
			default:
				event = null;
			}
			event.composeEvent(changedTask);
		}
	}
}
