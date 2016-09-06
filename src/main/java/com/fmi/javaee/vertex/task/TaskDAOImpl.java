package com.fmi.javaee.vertex.task;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import com.fmi.javaee.vertex.event.compose.EventFactory;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

class TaskDAOImpl implements TaskDAO {

	private final EntityManager entityManager;

	@Inject
	TaskDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public TaskEntity createTask(TaskEntity task) {
		task.setCreationTime(new Date());
		task.setModificationTime(task.getCreationTime());
		task.setLastModificator(task.getCreator());
		task.setStatus(Status.NEW);

		entityManager.persist(task);
		return task;
	}

	@Override
	@Transactional
	public TaskEntity updateTask(TaskEntity task) {
		TaskEntity oldTask = getTaskById(task.getTaskId());
		TaskEntity updatedTask = entityManager.merge(task);
		createEvents(oldTask, updatedTask);
		return updatedTask;
	}

	private void createEvents(TaskEntity oldTask, TaskEntity newTask) {
		if (!StringUtils.isBlank(newTask.getDefinition()) && !oldTask.getDefinition().equals(newTask.getDefinition())) {
			EventFactory.getInstance().getEventByComponent(Component.DEFINITION).composeEvent(newTask);
		}

		if (!oldTask.getPriority().equals(newTask.getPriority())) {
			EventFactory.getInstance().getEventByComponent(Component.PRIORITY).composeEvent(newTask);
		}

		if (!oldTask.getStatus().equals(newTask.getStatus())) {
			EventFactory.getInstance().getEventByComponent(Component.STATUS).composeEvent(newTask);
		}

	}

	@Override
	public TaskEntity getTaskById(String id) {
		return entityManager.find(TaskEntity.class, id);
	}

	@Override
	public List<TaskEntity> getTasksByAssignee(UserEntity asignee) {
		TypedQuery<TaskEntity> getByAsigneeQuery = entityManager.createNamedQuery(TaskEntity.GET_BY_ASIGNEE,
				TaskEntity.class);
		getByAsigneeQuery.setParameter("asignee", asignee);
		return getByAsigneeQuery.getResultList();
	}

	@Override
	public List<TaskEntity> getTasksByCreator(UserEntity creator) {
		TypedQuery<TaskEntity> getByAsigneeQuery = entityManager.createNamedQuery(TaskEntity.GET_BY_CREATOR,
				TaskEntity.class);
		getByAsigneeQuery.setParameter("creator", creator);
		return getByAsigneeQuery.getResultList();
	}

}
