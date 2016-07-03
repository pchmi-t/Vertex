package com.fmi.javaee.vertex.task;

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
		return null;
	}
	
	@PUT
	public Response updateTask(TaskBean updatedTask) {
		return null;
	}
	
	@GET
	@Path("/asignee/{username}")
	public Response getTaskByAssignee(@PathParam("username") String username) {
		return null;
	}
	
	@GET
	public Response getTasksByCriteria(@QueryParam("username") String username, 
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
		return null;
	}
}
