package com.fmi.javaee.vertex.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fmi.javaee.vertex.task.comment.Comment;
import com.fmi.javaee.vertex.task.comment.CommentEntity;
import com.fmi.javaee.vertex.user.User;
import com.fmi.javaee.vertex.user.UserEntity;

public class Task {

	private String taskId;

	private Date creationTime;

	private Date deadline;

	private Date modificationTime;

	private TaskStatus status;

	private Priority priority;

	private User asignee;

	private String definition;

	private User creator;

	private User lastModificator;

	private String title;

	private String projectName;

	private String projectId;

	private List<Comment> comments;

	private int subscribersCount;

	public Task(TaskEntity taskEntity) {
		this.taskId = taskEntity.getTaskId();
		this.creationTime = taskEntity.getCreationTime();
		this.deadline = taskEntity.getDeadline();
		this.modificationTime = taskEntity.getModificationTime();
		this.definition = taskEntity.getDefinition();
		this.priority = taskEntity.getPriority();
		this.projectId = taskEntity.getProject().getProjectId();
		this.projectName = taskEntity.getProject().getProjectName();
		this.asignee = getUser(taskEntity.getAsignee());
		this.creator = getUser(taskEntity.getCreator());
		this.lastModificator = getUser(taskEntity.getLastModificator());
		this.status = taskEntity.getStatus();
		this.title = taskEntity.getTitle();
		this.comments = convertComments(taskEntity.getComments());
		this.subscribersCount = taskEntity.getSubscriptions().size();
	}

	private List<Comment> convertComments(List<CommentEntity> commentEntities) {
		List<Comment> comments = new ArrayList<>();
		for (CommentEntity commentEntity : commentEntities) {
			comments.add(new Comment(commentEntity));
		}
		return comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setSubscribersCount(int subscribersCount) {
		this.subscribersCount = subscribersCount;
	}

	public int getSubscribersCount() {
		return subscribersCount;
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

	public String getProjectName() {
		return projectName;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
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
