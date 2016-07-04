package com.fmi.javaee.vertex.factory;

import com.fmi.javaee.vertex.event.data.EventDAO;
import com.fmi.javaee.vertex.event.data.impl.EventDataImpl;
import com.fmi.javaee.vertex.project.ProjectDAO;
import com.fmi.javaee.vertex.project.data.ProjectDAOImpl;
import com.fmi.javaee.vertex.task.data.TaskData;
import com.fmi.javaee.vertex.task.data.impl.TaskDataImpl;
import com.fmi.javaee.vertex.user.data.UserData;
import com.fmi.javaee.vertex.user.data.impl.UserDataImpl;

public class Factory {

	private Factory(){
	}
	
	public static Factory getInstance() {
		return new Factory();
	}
	
	public UserData getUserData() {
		return new UserDataImpl();
	}
	
	public TaskData getTaskData() {
		return new TaskDataImpl();
	}
	
	public EventDAO getEventDAO() {
		return new EventDataImpl();
	}
	
	public ProjectDAO getProjectDAO() {
		return new ProjectDAOImpl();
	}
}
