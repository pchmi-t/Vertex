package com.fmi.javaee.vertex.task;

import java.util.Date;

import com.fmi.javaee.vertex.user.User;
import com.fmi.javaee.vertex.user.UserEntity;

public class Task {

	private String taskId;

	private Date creationTime;

	private Date modificationTime;

	private Status status;

	private Priority priority;

	private User asignee;

	private String definition;

	private User creator;

	private User lastModificator;

	private String title;

	private String projectId;

	public Task(TaskEntity taskEntity) {
		this.taskId = taskEntity.getTaskId();
		this.creationTime = taskEntity.getCreationTime();
		this.modificationTime = taskEntity.getModificationTime();
		this.definition = taskEntity.getDefinition();
		this.priority = taskEntity.getPriority();
		this.projectId = taskEntity.getProject().getProjectId();
		this.asignee = getUser(taskEntity.getAsignee());
		this.creator = getUser(taskEntity.getCreator());
		this.lastModificator = getUser(taskEntity.getLastModificator());
		this.status = taskEntity.getStatus();
		this.title = taskEntity.getTitle();
	}

	private User getUser(UserEntity user) {
		return user != null ? new User(user) : null;
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

	public User getAsignee() {
		return asignee;
	}

	public void setAsignee(User asignee) {
		this.asignee = asignee;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getLastModificator() {
		return lastModificator;
	}

	public void setLastModificator(User lastModificator) {
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

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", creationTime=" + creationTime + ", modificationTime=" + modificationTime
				+ ", status=" + status + ", priority=" + priority + ", asignee=" + asignee + ", definition="
				+ definition + ", creator=" + creator + ", lastModificator=" + lastModificator + ", title=" + title
				+ ", projectId=" + projectId + "]";
	}

}
