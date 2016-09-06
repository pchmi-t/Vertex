package com.fmi.javaee.vertex.project;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

class ProjectDAOImpl implements ProjectDAO {
	
	private final EntityManager entityManager;

	@Inject
	ProjectDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<ProjectEntity> getProjectsOfUser(String userEmail) {
		TypedQuery<ProjectEntity> getByUserQuery = entityManager.createNamedQuery(ProjectEntity.GET_BY_USER, ProjectEntity.class);
		getByUserQuery.setParameter("mEmail", userEmail);
		return getByUserQuery.getResultList();
	}

	@Override
	@Transactional
	public ProjectEntity createProject(ProjectRequest projectRequest, Set<UserEntity> admins,
			Set<UserEntity> members) {
		ProjectEntity newProject = new ProjectEntity();
		newProject.setAdministrators(admins);
		newProject.setMembers(members);
		newProject.setCreationTime(new Date());
		newProject.setProjectDescription(projectRequest.getProjectDescription());
		newProject.setProjectName(projectRequest.getProjectName());
		
		entityManager.persist(newProject);
		return newProject;
	}

	@Override
	public ProjectEntity getProject(String projectId) {
		return entityManager.find(ProjectEntity.class, projectId);
	}

}
