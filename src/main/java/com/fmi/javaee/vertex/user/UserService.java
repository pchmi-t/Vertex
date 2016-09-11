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

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {
	
	private final UserDAO userDAO;
	
	@Inject
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GET
	public Response getLoggedUser(@Context HttpServletRequest request) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}

		UserEntity user = userDAO.getUserByEmail(loggedEmail);
		if (user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		return Response.ok(new User(user)).build();

	}

	@GET
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") Long userId) {
		UserEntity user = new UserEntity();
		user = userDAO.getUser(userId);
		if (user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		return Response.ok(new User(user)).build();

	}
	
	@GET
	@Path("/all")
	public Response getAllUsers() {
		List<UserEntity> userEntities = userDAO.getUsers(Integer.MAX_VALUE, 0);
		List<User> users = new ArrayList<>();
		for (UserEntity entity : userEntities) {
			users.add(new User(entity));
		}
		return Response.ok(users).build();

	}

}
