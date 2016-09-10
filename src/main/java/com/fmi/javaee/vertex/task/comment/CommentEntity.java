package com.fmi.javaee.vertex.task.comment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "comments")
public class CommentEntity {

	private String id;

	private TaskEntity commentedTask;

	private Date creationTime;

	private UserEntity commentator;

	private String content;

	@JoinColumn(name = "taskId")
	@ManyToOne(fetch = FetchType.LAZY)
	public TaskEntity getCommentedTask() {
		return commentedTask;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "commentId", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCommentedTask(TaskEntity commentedTask) {
		this.commentedTask = commentedTask;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.LAZY)
	public UserEntity getCommentator() {
		return commentator;
	}

	public void setCommentator(UserEntity commentator) {
		this.commentator = commentator;
	}

}
