package com.fmi.javaee.vertex.project.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fmi.javaee.vertex.project.ProjectDAO;
import com.fmi.javaee.vertex.project.ProjectEntity;
import com.fmi.javaee.vertex.project.ProjectRequest;
import com.fmi.javaee.vertex.session.SessionFactoryData;
import com.fmi.javaee.vertex.user.UserEntity;

public class ProjectDAOImpl implements ProjectDAO {

	@Override
	public List<ProjectEntity> getProjectsOfUser(String userEmail) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria.add(Restrictions.eq("email", userEmail));
			UserEntity userByEmail = (UserEntity) criteria.uniqueResult();
			if (userByEmail == null) {
				return new ArrayList<>();
			}
			return new ArrayList<>(userByEmail.getMemberProjects());
		} finally {
			SessionFactoryData.closeSession(session);
		}
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

		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.save(newProject);
			transaction.commit();
			return newProject;
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

}
