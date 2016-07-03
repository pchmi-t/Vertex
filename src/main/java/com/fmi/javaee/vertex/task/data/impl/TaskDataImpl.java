package com.fmi.javaee.vertex.task.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fmi.javaee.vertex.session.SessionFactoryData;
import com.fmi.javaee.vertex.task.Priority;
import com.fmi.javaee.vertex.task.Status;
import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.task.data.TaskData;
import com.fmi.javaee.vertex.user.UserBean;

public class TaskDataImpl implements TaskData {

	@Override
	public TaskBean createTask(TaskBean task) {
		Session session = SessionFactoryData.getSessionFactory().openSession();

		try {
			//TODO Should validate the task
			
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
			return task;
		} catch (HibernateException ex) {
			final String errorMessage = "A database error occured while saving/updating the task.";
			throw new RuntimeException(errorMessage, ex);
		} finally {
			SessionFactoryData.closeSession(session);
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
	public List<TaskBean> getTasksByAssignee(UserBean asignee) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(TaskBean.class);
			criteria.add(Restrictions.eq("asignee_id", asignee));
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
