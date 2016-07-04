package com.fmi.javaee.vertex.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.data.UserData;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

	@GET
	public Response getLoggedUser(@Context HttpServletRequest request) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}

		UserData userData = Factory.getInstance().getUserData();
		UserEntity user = userData.getUserByEmail(loggedEmail);
		if (user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		return Response.ok(new UserBean(user)).build();

	}

	@GET
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		UserEntity user = new UserEntity();
		UserData userData = Factory.getInstance().getUserData();
		user = userData.getUser(userId);
		if (user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		return Response.ok(new UserBean(user)).build();

	}
	
	@GET
	@Path("/all")
	public Response getAllUsers() {
		UserData userData = Factory.getInstance().getUserData();
		List<UserEntity> userEntities = userData.getUsers(Integer.MAX_VALUE, 0);
		List<UserBean> users = new ArrayList<>();
		for (UserEntity entity : userEntities) {
			users.add(new UserBean(entity));
		}
		return Response.ok(users).build();

	}

}
