package com.fmi.javaee.vertex.event;

import java.util.Date;

import com.fmi.javaee.vertex.task.Component;

public class EventBean {

	private String eventId;
	private Date creationTime;
	private String description;
	private String taskId;
	private String userEmail;
	private Component eventComponent;

	public EventBean() {
	}

	public EventBean(EventEntity entity) {
		this.eventId = entity.getEventId();
		this.description = entity.getDescription();
		this.creationTime = entity.getCreationTime();
		this.eventComponent = entity.getEventComponent();
		this.taskId = entity.getRefTask() != null ? entity.getRefTask().getTaskId() : null;
		this.userEmail = entity.getRefUser() != null ? entity.getRefUser().getEmail() : null;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Component getEventComponent() {
		return eventComponent;
	}

	public void setEventComponent(Component eventComponent) {
		this.eventComponent = eventComponent;
	}

}
