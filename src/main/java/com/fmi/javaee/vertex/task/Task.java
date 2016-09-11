package com.fmi.javaee.vertex.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fmi.javaee.vertex.task.comment.Comment;
import com.fmi.javaee.vertex.task.comment.CommentDateComparator;
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

	private String title;

	private String projectName;

	private long projectId;

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
		this.status = taskEntity.getStatus();
		this.title = taskEntity.getTitle();
		this.comments = convertComments(taskEntity.getComments());
		this.subscribersCount = taskEntity.getSubscriptions().size();
	}

	private List<Comment> convertComments(Set<CommentEntity> commentEntities) {
		List<Comment> comments = new ArrayList<>();
		for (CommentEntity commentEntity : commentEntities) {
			comments.add(new Comment(commentEntity));
		}
		Collections.sort(comments, new CommentDateComparator());
		return comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Date getDeadline() {
		return deadline;
	}

	public int getSubscribersCount() {
		return subscribersCount;
	}

	private User getUser(UserEntity user) {
		return user != null ? new User(user) : null;
	}

	public String getTaskId() {
		return taskId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public Priority getPriority() {
		return priority;
	}

	public User getAsignee() {
		return asignee;
	}

	public String getDefinition() {
		return definition;
	}

	public User getCreator() {
		return creator;
	}

	public String getTitle() {
		return title;
	}

	public long getProjectId() {
		return projectId;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", creationTime=" + creationTime + ", modificationTime=" + modificationTime
				+ ", status=" + status + ", priority=" + priority + ", asignee=" + asignee + ", definition="
				+ definition + ", creator=" + creator + ", title=" + title
				+ ", projectId=" + projectId + "]";
	}

}
