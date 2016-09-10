package com.fmi.javaee.vertex.task;

import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.project.ProjectDAO;
import com.fmi.javaee.vertex.project.ProjectEntity;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.task.event.EventType;
import com.fmi.javaee.vertex.user.UserDAO;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

@Path("task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

	private final TaskDAO taskDAO;
	private final UserDAO userDAO;
	private final ProjectDAO projectDAO;
	private final EventBus eventBus;

	@Inject
	public TaskService(TaskDAO taskDAO, UserDAO userDAO, ProjectDAO projectDAO, EventBus eventBus) {
		this.projectDAO = projectDAO;
		this.taskDAO = taskDAO;
		this.userDAO = userDAO;
		this.eventBus = eventBus;
	}

	@GET
	@Path("/{taskId}")
	public Response getTask(@Context HttpServletRequest request, @PathParam("taskId") String taskId) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			LOGGER.error("Failed attempt to retrieve user tasks!");
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		} else if (taskId == null) {
			return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
		}

		LOGGER.info("Retrieving task with ID [{}]", taskId);

		TaskEntity task = taskDAO.getTaskById(taskId);
		if (task == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		} else {
			return Response.ok(new Task(task)).build();
		}

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
			return Response.ok().entity(new Task(createdTask)).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	public Response createTask(TaskEntity task) {
		TaskEntity createdTask = taskDAO.createTask(task);
		if (createdTask != null) {
			return Response.ok().entity(new Task(createdTask)).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("{taskId}/assign")
	public Response assignTask(@PathParam("taskId") String taskId, AssignmentRequest taskAssignment) {
		String requestedAssignee = taskAssignment.getAssignee();
		if (taskAssignment == null || requestedAssignee == null) {
			LOGGER.error("Received invalid task assignment request with missing assignee");
			return Response.status(Status.BAD_REQUEST).build();
		}

		UserEntity assignee = userDAO.getUserByEmail(requestedAssignee);
		if (assignee == null) {
			LOGGER.error("Invalid user in task assignment request: [{}]", requestedAssignee);
			return Response.status(Status.BAD_REQUEST).build();
		}

		TaskEntity task = taskDAO.getTaskById(taskId);
		LOGGER.debug("Creating task assignment event...");
		String previsousAsignee = task.getAsignee() != null ? task.getAsignee().getEmail() : null;

		createEvent(EventType.ASSIGNMENT, previsousAsignee, requestedAssignee, assignee, task);

		LOGGER.debug("Assigning task [{}] to user [{}]", taskId, requestedAssignee);
		task.setAsignee(assignee);
		taskDAO.updateTask(task);

		return Response.ok(new Task(task)).build();
	}

	@PUT
	@Path("{taskId}/status")
	public Response changeTaskStatus(@Context HttpServletRequest request, @PathParam("taskId") String taskId, ChangeStatusRequest statusChangeRequest) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			LOGGER.error("Failed attempt to retrieve user tasks!");
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}
		UserEntity refUser = userDAO.getUserByEmail(loggedEmail);

		TaskEntity task = taskDAO.getTaskById(taskId);
		TaskStatus requestedStatus = statusChangeRequest.getStatus();

		createEvent(EventType.STATUS, task.getStatus().name(), requestedStatus.name(), refUser, task);

		LOGGER.debug("Setting the status of task [{}] to [{}]", taskId, requestedStatus);
		task.setStatus(requestedStatus);
		taskDAO.updateTask(task);

		return Response.ok(new Task(task)).build();
	}
	
	@PUT
	@Path("{taskId}/priority")
	public Response changeTaskPriority(@Context HttpServletRequest request, @PathParam("taskId") String taskId, ChangePriorityRequest prioChangeRequest) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			LOGGER.error("Failed attempt to retrieve user tasks!");
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}
		UserEntity refUser = userDAO.getUserByEmail(loggedEmail);

		TaskEntity task = taskDAO.getTaskById(taskId);
		Priority newPriority = prioChangeRequest.getPriority();

		createEvent(EventType.PRIORITY, task.getPriority().name(), newPriority.name(), refUser, task);

		LOGGER.debug("Setting the priority of task [{}] to [{}]", taskId, newPriority);
		task.setPriority(newPriority);
		taskDAO.updateTask(task);

		return Response.ok(new Task(task)).build();
	}
	
	private void createEvent(EventType type, String before, String after, UserEntity refUser, TaskEntity refTask) {
		EventEntity event = new EventEntity();
		event.setBefore(before);
		event.setAfter(after);
		event.setRefTask(refTask);
		event.setRefUser(refUser);
		event.setType(type);
		event.setTimestamp(new Date());
		eventBus.post(event);
	}

	@GET
	@Path("/asignee/")
	public Response getTaskByAssignee(@Context HttpServletRequest request) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			LOGGER.error("Failed attempt to retrieve user tasks!");
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}

		LOGGER.info("Retrieving all tasks assigned to user [{}]", loggedEmail);
		UserEntity user = userDAO.getUserByEmail(loggedEmail);

		List<TaskEntity> usersTaskEntities = taskDAO.getTasksByAssignee(user);
		return getByUser(usersTaskEntities);
	}

	@GET
	@Path("/creator/")
	public Response getTaskByCreator(@Context HttpServletRequest request) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			LOGGER.error("Failed attempt to retrieve user tasks!");
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}

		LOGGER.info("Retrieving all tasks created by user [{}]", loggedEmail);
		UserEntity user = userDAO.getUserByEmail(loggedEmail);

		List<TaskEntity> usersTaskEntities = taskDAO.getTasksByCreator(user);
		return getByUser(usersTaskEntities);
	}

	private Response getByUser(List<TaskEntity> usersTaskEntities) {
		if (usersTaskEntities != null && !usersTaskEntities.isEmpty()) {
			List<Task> tasks = new ArrayList<>();
			for (TaskEntity task : usersTaskEntities) {
				tasks.add(new Task(task));
			}

			return Response.ok().entity(tasks).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
