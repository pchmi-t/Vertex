package com.fmi.javaee.vertex.user;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	
	@GET
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		UserBean user = new UserBean();
		user.setUserId(userId);
		user.setName("Tanya Hristowa");
		user.setGender(Gender.FEMALE);
		LocalDateTime start = LocalDateTime.of(2016, 6, 6, 19, 46, 45);
		user.setAverageTaskEcecutionTime(Duration.between(start, LocalDateTime.now()));
		user.setTitle("Web Developer");
		return Response.ok(user).build();
	}
	
	
}
