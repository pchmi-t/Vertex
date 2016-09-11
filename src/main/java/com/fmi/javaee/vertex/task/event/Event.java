package com.fmi.javaee.vertex.task.event;

import java.util.Date;

public class Event {

	private Date timestamp;
	private String before;
	private String after;
	private EventType type;
	private String taskId;
	private String userEmail;

	public Event(EventEntity entity) {
		this.timestamp = entity.getTimestamp();
		this.after = entity.getAfter();
		this.before = entity.getBefore();
		this.type = entity.getType();
		this.taskId = entity.getRefTask() != null ? entity.getRefTask().getTaskId() : null;
		this.userEmail = entity.getRefUser() != null ? entity.getRefUser().getEmail() : null;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	
	

}
