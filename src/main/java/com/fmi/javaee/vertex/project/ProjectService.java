package com.fmi.javaee.vertex.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.UserEntity;
import com.fmi.javaee.vertex.user.data.UserData;

@Path("project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectService {
	
	@GET
	public Response getUserTasks(@Context HttpServletRequest request) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}
		
		ProjectDAO projectDAO = Factory.getInstance().getProjectDAO();
		List<ProjectEntity> projectsOfUser = projectDAO.getProjectsOfUser(loggedEmail);
		if (projectsOfUser.isEmpty()) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		
		List<ProjectBean> projects = new ArrayList<>();
		for (ProjectEntity projectEntity : projectsOfUser) {
			projects.add(new ProjectBean(projectEntity));
		}
		return Response.ok(projects).build();
	}
	
	@POST
	public Response createProject(@Context HttpServletRequest request, ProjectRequest projectRequest) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}

		Set<String> requestedAdmins = projectRequest.getAdminEmails();
		Set<String> requestedMembers = projectRequest.getMemberEmails();

		requestedAdmins.add(loggedEmail);
		requestedMembers.addAll(requestedAdmins);
		
		UserData userData = Factory.getInstance().getUserData();
		Set<UserEntity> members = new HashSet<>();
		for (String memberEmail : requestedMembers) {
			UserEntity member = userData.getUserByEmail(memberEmail);
			if (member == null) {
				return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
			}
			members.add(member);
		}
		
		Set<UserEntity> admins = new HashSet<>();
		for (String adminEmail : requestedAdmins) {
			UserEntity admin = userData.getUserByEmail(adminEmail);
			if (admin == null) {
				return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
			}
			admins.add(admin);
		}
		
		ProjectDAO projectDAO = Factory.getInstance().getProjectDAO();
		ProjectEntity createdProject = projectDAO.createProject(projectRequest, admins, members);
		return Response.status(HttpServletResponse.SC_CREATED).entity(new ProjectBean(createdProject)).build();
	}

}
