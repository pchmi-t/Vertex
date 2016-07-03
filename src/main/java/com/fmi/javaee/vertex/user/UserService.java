package com.fmi.javaee.vertex.user;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.data.UserData;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

	@GET
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		UserBean user = new UserBean();
		UserData userData = Factory.getInstance().getUserData();
		user = userData.getUser(userId);
		if (user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		return Response.ok(user).build();

	}

	@POST
	public Response createUser(@Context UriInfo uriInfo, UserBean user) {
		// TODO Check for permissions
		UserData userData = Factory.getInstance().getUserData();
		UserBean domainUser = userData.createUser(user);
		if (domainUser == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			URI userURI = uriInfo.getAbsolutePathBuilder().path(domainUser.getUserId()).build();
			return Response.created(userURI).build();
		}
	}
}
