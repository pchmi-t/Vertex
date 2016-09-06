package com.fmi.javaee.vertex.task;

import java.util.Date;

import com.fmi.javaee.vertex.user.UserEntity;

public class Task {

	private String taskId;

	private Date creationTime;

	private Date modificationTime;

	private Status status;

	private Priority priority;

	private String asignee;

	private String definition;

	private String creator;

	private String lastModificator;

	private String title;

	private String projectId;

	public Task(TaskEntity taskEntity) {
		this.taskId = taskEntity.getTaskId();
		this.creationTime = taskEntity.getCreationTime();
		this.modificationTime = taskEntity.getModificationTime();
		this.definition = taskEntity.getDefinition();
		this.priority = taskEntity.getPriority();
		this.projectId = taskEntity.getProject().getProjectId();
		this.asignee = getUserEmail(taskEntity.getAsignee());
		this.creator = getUserEmail(taskEntity.getCreator());
		this.lastModificator = getUserEmail(taskEntity.getLastModificator());
		this.status = taskEntity.getStatus();
		this.title = taskEntity.getTitle();
	}

	public Task() {
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getAsignee() {
		return asignee;
	}

	public void setAsignee(String asignee) {
		this.asignee = asignee;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLastModificator() {
		return lastModificator;
	}

	public void setLastModificator(String lastModificator) {
		this.lastModificator = lastModificator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	private String getUserEmail(UserEntity user) {
		return user != null ? user.getEmail() : null;
	}

}
