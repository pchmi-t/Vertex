package com.fmi.javaee.vertex.task;

import java.util.Date;
import java.util.Observable;

import com.fmi.javaee.vertex.task.monitoring.Component;

public class Task extends Observable {
	private Status status;
	
	private String definition;
	
	private Priority priority;
	
	private Date modificationTime;

	private Long taskId;

	/** <b>NOTE!</b> Use this constructor only if this is the creation of the task. */
	public Task(Status status, String definition, Priority priority, Date modificationTime) {
		super();
		this.status = status;
		this.definition = definition;
		this.priority = priority;
		this.modificationTime = modificationTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
		setChanged();
		notifyObservers(Component.STATUS);
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
		setChanged();
		notifyObservers(Component.DEFINITION);
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
		setChanged();
		notifyObservers(Component.PRIORITY);
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	
}
