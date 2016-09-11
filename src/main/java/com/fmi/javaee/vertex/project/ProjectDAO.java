package com.fmi.javaee.vertex.project;

import java.util.List;
import java.util.Set;

import com.fmi.javaee.vertex.user.UserEntity;

public interface ProjectDAO {

	public ProjectEntity getProject(long projectId);

	public ProjectEntity createProject(ProjectRequest projectRequest, Set<UserEntity> admins, Set<UserEntity> members);

	public List<ProjectEntity> getProjectsOfUser(String userEmail);

}
