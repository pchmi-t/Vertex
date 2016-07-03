package com.fmi.javaee.vertex.user;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.data.UserData;

@Path("register")
public class RegisterService {
	
	@POST
	public Response createUser(UserBean user) {
		// TODO Check for permissions
		UserData userDAO = Factory.getInstance().getUserData();
		UserBean createdUser = userDAO.createUser(user);
		if (createdUser == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			return Response.status(HttpServletResponse.SC_CREATED).entity(createdUser).build();
		}
	}
}
