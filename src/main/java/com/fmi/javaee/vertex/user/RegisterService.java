package com.fmi.javaee.vertex.user;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.data.UserData;

@Path("register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterService {
	
	@POST
	public Response createUser(UserEntity user) {
		// TODO Check for permissions
		UserData userDAO = Factory.getInstance().getUserData();
		UserEntity existingUser = userDAO.getUserByEmail(user.getEmail());
		if (existingUser != null) {
			return Response.status(Status.CONFLICT).build();
		}
		
		UserEntity createdUser = userDAO.createUser(user);
		if (createdUser == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			return Response.status(HttpServletResponse.SC_CREATED).entity(new UserBean(createdUser)).build();
		}
	}
}
