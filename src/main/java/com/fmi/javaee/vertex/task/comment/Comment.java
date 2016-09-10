package com.fmi.javaee.vertex.task.comment;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Comment {

	private Date creationTime;

	private String commentator;

	private String content;

	public Comment(CommentEntity entity) {
		this.creationTime = entity.getCreationTime();
		this.commentator = entity.getCommentator().getFullName();
		this.content = entity.getContent();
	}

	public Comment() {
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public String getCommentator() {
		return commentator;
	}

}
