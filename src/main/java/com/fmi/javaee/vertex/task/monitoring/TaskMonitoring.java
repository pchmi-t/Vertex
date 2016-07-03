package com.fmi.javaee.vertex.task.monitoring;

import java.util.Observable;
import java.util.Observer;

import com.fmi.javaee.vertex.task.TaskBean;

public class TaskMonitoring implements Observer {

	private TaskBean changedTask;

	@Override
	public void update(Observable o, Object component) {
		changedTask = (TaskBean) o;
		Component componentChanged = (Component) component;
		switch (componentChanged) {
		case STATUS:
			break;
		case DEFINITION:
			break;
		case PRIORITY:
			break;
		default:
			break;
		}
	}
}
