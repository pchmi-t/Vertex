package com.fmi.javaee.vertex.task;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.task.data.TaskData;
import com.fmi.javaee.vertex.user.UserBean;
import com.fmi.javaee.vertex.user.data.UserData;

@Path("task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskService {

	private TaskData taskData = Factory.getInstance().getTaskData();
	
	@POST
	public Response createTask(TaskBean task) {
		TaskBean createdTask = taskData.createTask(task);
		if (createdTask != null) {
			return Response.ok().entity(createdTask).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Path("{taskId}/{username}")
	public Response assignTask(@PathParam("taskId") String taskId, 
			@PathParam("username") String username) {
		//TODO TBD
		return null;
	}
	
	@PUT
	public Response updateOrUpdateTask(TaskBean task) {
		TaskBean updatedTask = taskData.updateTask(task);
		if (updatedTask != null) {
			return Response.ok().entity(updatedTask).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/asignee/{email}")
	public Response getTaskByAssignee(@PathParam("email") String email) {
		UserData userData = Factory.getInstance().getUserData();
		UserBean user = userData.getUserByEmail(email);
		if (user == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<TaskBean> usersTasks = taskData.getTasksByAssignee(user);
		if (usersTasks != null && !usersTasks.isEmpty()) {
			return Response.ok().entity(usersTasks).build();
		} else {
			return Response.ok().entity(new LinkedList<TaskBean>()).build();
		}
	}
	
	@GET
	public Response getTasksByCriteria(@QueryParam("username") String username, 
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
		//TODO TBD
		return null;
	}
}
