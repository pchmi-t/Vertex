package com.fmi.javaee.vertex.task;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;
import com.google.inject.Provider;

class TaskDAOImpl implements TaskDAO {

	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	TaskDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	public TaskEntity createTask(TaskEntity task) {
		task.setCreationTime(new Date());
		task.setModificationTime(task.getCreationTime());
		task.setLastModificator(task.getCreator());
		task.setStatus(TaskStatus.NEW);

		EntityManager entityManager = entityManagerProvider.get();
		entityManager.getTransaction().begin();
		entityManager.persist(task);
		entityManager.getTransaction().commit();
		return task;
	}

	@Override
	public TaskEntity updateTask(TaskEntity task) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.getTransaction().begin();
		TaskEntity updatedTask = entityManager.merge(task);
		entityManager.getTransaction().commit();
		return updatedTask;
	}

	@Override
	public TaskEntity getTaskById(String id) {
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.find(TaskEntity.class, id);
	}

	@Override
	public List<TaskEntity> getTasksByAssignee(UserEntity asignee) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<TaskEntity> getByAsigneeQuery = entityManager.createNamedQuery(TaskEntity.GET_BY_ASIGNEE,
				TaskEntity.class);
		getByAsigneeQuery.setParameter("asignee", asignee);
		return getByAsigneeQuery.getResultList();
	}

	@Override
	public List<TaskEntity> getTasksByCreator(UserEntity creator) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<TaskEntity> getByAsigneeQuery = entityManager.createNamedQuery(TaskEntity.GET_BY_CREATOR,
				TaskEntity.class);
		getByAsigneeQuery.setParameter("creator", creator);
		return getByAsigneeQuery.getResultList();
	}

}
