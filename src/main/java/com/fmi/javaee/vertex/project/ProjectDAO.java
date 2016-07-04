package com.fmi.javaee.vertex.project;

import java.util.List;

import com.fmi.javaee.vertex.user.UserEntity;

public interface ProjectDAO {
	
	public List<ProjectEntity> getProjectsOfUser(String userEmail);

	public ProjectEntity createProject(ProjectRequest projectRequest, List<UserEntity> admins, List<UserEntity> members);

}
