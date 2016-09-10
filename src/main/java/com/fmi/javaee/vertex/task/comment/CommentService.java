package com.fmi.javaee.vertex.task.comment;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.javaee.vertex.task.TaskDAO;
import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.task.event.EventType;
import com.fmi.javaee.vertex.user.UserDAO;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

@Path("task/{taskId}/comment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentService {

	private final TaskDAO taskDAO;
	private final UserDAO userDAO;
	private final CommentDAO commentDAO;
	private final EventBus eventBus;

	@Inject
	public CommentService(CommentDAO commentDAO, TaskDAO taskDAO, UserDAO userDAO, EventBus eventBus) {
		this.taskDAO = taskDAO;
		this.commentDAO = commentDAO;
		this.userDAO = userDAO;
		this.eventBus = eventBus;
	}

	@POST
	public Response createComment(@Context HttpServletRequest request, @PathParam("taskId") String taskId,
			Comment comment) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}
		UserEntity creator = userDAO.getUserByEmail(loggedEmail);

		TaskEntity commentedTask = taskDAO.getTaskById(taskId);
		if (taskId == null || commentedTask == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}

		CommentEntity commentEntity = createCommentEntity(comment, creator, commentedTask);
		createEvent(commentEntity);
		commentedTask.addComment(commentEntity);
		commentDAO.save(commentEntity);

		return Response.status(Status.CREATED).entity(comment).build();
	}

	private CommentEntity createCommentEntity(Comment comment, UserEntity creator, TaskEntity commentedTask) {
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setCommentator(creator);
		commentEntity.setCommentedTask(commentedTask);
		commentEntity.setContent(comment.getContent());
		commentEntity.setCreationTime(new Date());
		return commentEntity;
	}

	private void createEvent(CommentEntity commentEntity) {
		EventEntity event = new EventEntity();
		event.setAfter(commentEntity.getContent());
		event.setRefTask(commentEntity.getCommentedTask());
		event.setRefUser(commentEntity.getCommentator());
		event.setTimestamp(commentEntity.getCreationTime());
		event.setType(EventType.COMMENT);
		eventBus.post(event);
	}

}
