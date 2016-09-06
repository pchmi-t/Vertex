package com.fmi.javaee.vertex.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.javaee.vertex.project.ProjectDAO;
import com.fmi.javaee.vertex.project.ProjectEntity;
import com.fmi.javaee.vertex.user.UserDAO;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;

@Path("task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskService {

	private final TaskDAO taskDAO;
	private final UserDAO userDAO;
	private final ProjectDAO projectDAO;

	@Inject
	public TaskService(TaskDAO taskDAO, UserDAO userDAO, ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
		this.taskDAO = taskDAO;
		this.userDAO = userDAO;
	}

	@POST
	@Path("project/{projectId}")
	public Response createTask(@Context HttpServletRequest request, TaskEntity taskRequest,
			@PathParam("projectId") String projectId) {
		ProjectEntity project = projectDAO.getProject(projectId);

		if (project == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}
		UserEntity creator = userDAO.getUserByEmail(loggedEmail);

		taskRequest.setProject(project);
		taskRequest.setCreator(creator);

		TaskEntity createdTask = taskDAO.createTask(taskRequest);
		if (createdTask != null) {
			return Response.ok().entity(createdTask).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	public Response createTask(TaskEntity task) {
		TaskEntity createdTask = taskDAO.createTask(task);
		if (createdTask != null) {
			return Response.ok().entity(createdTask).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("{taskId}/{username}")
	public Response assignTask(@PathParam("taskId") String taskId, @PathParam("username") String username) {
		// TODO TBD
		return null;
	}

	@PUT
	public Response updateOrUpdateTask(TaskEntity task) {
		TaskEntity updatedTask = taskDAO.updateTask(task);
		if (updatedTask != null) {
			return Response.ok().entity(updatedTask).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/asignee/{email}")
	public Response getTaskByAssignee(@PathParam("email") String email) {
		UserEntity user = userDAO.getUserByEmail(email);
		if (user == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<TaskEntity> usersTasks = taskDAO.getTasksByAssignee(user);
		if (usersTasks != null && !usersTasks.isEmpty()) {
			return Response.ok().entity(usersTasks).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	public Response getTasksByCriteria(@QueryParam("username") String username, @QueryParam("limit") Integer limit,
			@QueryParam("offset") Integer offset) {
		// TODO TBD
		return null;
	}
}
