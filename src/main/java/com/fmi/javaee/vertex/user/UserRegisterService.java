package com.fmi.javaee.vertex.user;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@Path("register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRegisterService {

	@Inject
	private UserDAO userDAO;

	@POST
	public Response createUser(UserEntity user) {
		UserEntity existingUser = userDAO.getUserByEmail(user.getEmail());
		if (existingUser != null) {
			return Response.status(Status.CONFLICT).build();
		}

		UserEntity createdUser = userDAO.createUser(user);
		if (createdUser == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			return Response.status(HttpServletResponse.SC_CREATED).entity(new User(createdUser)).build();
		}
	}
}
