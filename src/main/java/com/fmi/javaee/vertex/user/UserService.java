package com.fmi.javaee.vertex.user;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.data.UserCriterion;
import com.fmi.javaee.vertex.user.data.UserData;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	
	@GET
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") Long userId) {
		UserBean user = new UserBean();
		UserData userData = Factory.getInstance().getUserData();
		UserCriterion criterion = userData.getUserCriterion();
		criterion.id(userId);
		user = userData.getUser(criterion);
		return Response.ok(user).build();
	}
	
	@POST
	//TODO Add permissions
	public Response createUser(UserBean user) {
		//TODO Check for permissions
		UserData userData = Factory.getInstance().getUserData();
		UserBean domainUser = userData.createUser(user);
		if (domainUser == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			return Response.ok().entity(domainUser).build();
		}
	}
}
