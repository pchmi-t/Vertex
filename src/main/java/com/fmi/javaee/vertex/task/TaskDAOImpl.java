package com.fmi.javaee.vertex.task;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
class TaskDAOImpl implements TaskDAO {

	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	TaskDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	@Transactional
	public TaskEntity createTask(TaskEntity task) {
		task.setCreationTime(new Date());
		task.setModificationTime(task.getCreationTime());
		task.setStatus(TaskStatus.NEW);

		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(task);
		return task;
	}

	@Override
	@Transactional
	public TaskEntity updateTask(TaskEntity task) {
		EntityManager entityManager = entityManagerProvider.get();
		TaskEntity updatedTask = entityManager.merge(task);
		return updatedTask;
	}

	@Override
	@Transactional
	public TaskEntity getTaskById(String id) {
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.find(TaskEntity.class, id);
	}

	@Override
	@Transactional
	public List<TaskEntity> getTasksByAssignee(UserEntity asignee) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<TaskEntity> getByAsigneeQuery = entityManager.createNamedQuery(TaskEntity.GET_BY_ASIGNEE,
				TaskEntity.class);
		getByAsigneeQuery.setParameter("asignee", asignee);
		return getByAsigneeQuery.getResultList();
	}

	@Override
	@Transactional
	public List<TaskEntity> getTasksByCreator(UserEntity creator) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<TaskEntity> getByAsigneeQuery = entityManager.createNamedQuery(TaskEntity.GET_BY_CREATOR,
				TaskEntity.class);
		getByAsigneeQuery.setParameter("creator", creator);
		return getByAsigneeQuery.getResultList();
	}

}
