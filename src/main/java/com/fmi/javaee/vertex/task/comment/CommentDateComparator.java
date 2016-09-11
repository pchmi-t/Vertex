package com.fmi.javaee.vertex.task.comment;

import java.util.Comparator;

public class CommentDateComparator implements Comparator<Comment> {

	@Override
	public int compare(Comment comment1, Comment comment2) {
		return comment1.getCreationTime().compareTo(comment2.getCreationTime());
	}

}
