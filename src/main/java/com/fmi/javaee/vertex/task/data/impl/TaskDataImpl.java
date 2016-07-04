package com.fmi.javaee.vertex.task.data.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fmi.javaee.vertex.event.compose.EventFactory;
import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.session.SessionFactoryData;
import com.fmi.javaee.vertex.task.Priority;
import com.fmi.javaee.vertex.task.Status;
import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.task.data.TaskData;
import com.fmi.javaee.vertex.task.monitoring.Component;
import com.fmi.javaee.vertex.user.UserEntity;

public class TaskDataImpl implements TaskData {

	@Override
	public TaskBean createTask(TaskBean task) {
		Session session = SessionFactoryData.getSessionFactory().openSession();

		try {
			task.setCreationTime(new Date());
			task.setModificationTime(task.getCreationTime());
			task.setLastModificator(task.getCreator());
			task.setStatus(Status.NEW);
			
			Transaction tx = session.beginTransaction();
			session.save(task);
			
			tx.commit();
			return task;
		} catch (HibernateException ex) {
			final String errorMessage = "A database error occured while saving the task.";
			throw new RuntimeException(errorMessage, ex);
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public TaskBean updateTask(TaskBean task) {
		TaskBean oldTask = getTaskById(task.getTaskId());
		
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			//TODO Should validate the task
			
			Transaction tx = session.beginTransaction();
			if (task.getTaskId() == null) {
				session.save(task);
			} else {
				session.replicate(task, ReplicationMode.OVERWRITE);
			}
			tx.commit();
			createEvents(oldTask, task);
			return task;
		} catch (HibernateException ex) {
			final String errorMessage = "A database error occured while saving/updating the task.";
			throw new RuntimeException(errorMessage, ex);
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	private void createEvents(TaskBean oldTask, TaskBean newTask) {
		if (newTask != null) {		
			//The Definition has changed
			if (!StringUtils.isBlank(newTask.getDefinition()) && 
					!oldTask.getDefinition().equals(newTask.getDefinition())) {
				EventFactory.getInstance().getEventByComponent(Component.DEFINITION).composeEvent(newTask);
			}
			
			if (!oldTask.getPriority().equals(newTask.getPriority())) {
				EventFactory.getInstance().getEventByComponent(Component.PRIORITY).composeEvent(newTask);
			}
			
			if (!oldTask.getStatus().equals(newTask.getStatus())) {
				EventFactory.getInstance().getEventByComponent(Component.STATUS).composeEvent(newTask);
			}
			
		}
	}

	@Override
	public TaskBean getTaskById(String id) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(TaskBean.class);
			criteria.add(Restrictions.eq("taskId", id));
			TaskBean task = (TaskBean) criteria.uniqueResult();
			if ( task != null) {
				return task;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public List<TaskBean> getTasksByAssignee(UserEntity asignee) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(TaskBean.class);
			criteria.add(Restrictions.eq("asignee", asignee));
			@SuppressWarnings("unchecked")
			List<TaskBean> tasks = criteria.list();
			if ( tasks != null) {
				return tasks;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public List<TaskBean> getTasksByPriority(Priority priority) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(TaskBean.class);
			criteria.add(Restrictions.eq("priority", priority));
			@SuppressWarnings("unchecked")
			List<TaskBean> tasks = criteria.list();
			if ( tasks != null) {
				return tasks;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public List<TaskBean> getTasksByStatus(Status status) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(TaskBean.class);
			criteria.add(Restrictions.eq("status", status));
			@SuppressWarnings("unchecked")
			List<TaskBean> tasks = criteria.list();
			if ( tasks != null) {
				return tasks;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}
}
