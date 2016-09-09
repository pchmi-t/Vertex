package com.fmi.javaee.vertex.project;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;
import com.google.inject.Provider;

class ProjectDAOImpl implements ProjectDAO {
	
	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	ProjectDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	public List<ProjectEntity> getProjectsOfUser(String userEmail) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<ProjectEntity> getByUserQuery = entityManager.createNamedQuery(ProjectEntity.GET_BY_USER, ProjectEntity.class);
		getByUserQuery.setParameter("mEmail", userEmail);
		return getByUserQuery.getResultList();
	}

	@Override
	public ProjectEntity createProject(ProjectRequest projectRequest, Set<UserEntity> admins,
			Set<UserEntity> members) {
		ProjectEntity newProject = new ProjectEntity();
		newProject.setAdministrators(admins);
		newProject.setMembers(members);
		newProject.setCreationTime(new Date());
		newProject.setProjectDescription(projectRequest.getProjectDescription());
		newProject.setProjectName(projectRequest.getProjectName());
		
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.getTransaction().begin();
		entityManager.persist(newProject);
		entityManager.getTransaction().commit();
		return newProject;
	}

	@Override
	public ProjectEntity getProject(String projectId) {
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.find(ProjectEntity.class, projectId);
	}

}
