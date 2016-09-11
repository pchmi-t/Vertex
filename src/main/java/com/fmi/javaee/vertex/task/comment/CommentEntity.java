package com.fmi.javaee.vertex.task.comment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "comments")
public class CommentEntity {

	@Id
	@GeneratedValue
	@Column(name = "commentId")
	private long id;

	@ManyToOne
	@JoinColumn(name = "taskId")
	private TaskEntity commentedTask;

	@Column(name = "creationTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity commentator;

	@Column(name="content", length=2000)
	private String content;
	
	public TaskEntity getCommentedTask() {
		return commentedTask;
	}

	
	public long getId() {
		return id;
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

	public UserEntity getCommentator() {
		return commentator;
	}

	public void setCommentator(UserEntity commentator) {
		this.commentator = commentator;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentEntity other = (CommentEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
