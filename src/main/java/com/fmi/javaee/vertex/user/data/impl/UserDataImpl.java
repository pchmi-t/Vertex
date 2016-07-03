package com.fmi.javaee.vertex.user.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fmi.javaee.vertex.session.SessionFactoryData;
import com.fmi.javaee.vertex.user.Gender;
import com.fmi.javaee.vertex.user.UserBean;
import com.fmi.javaee.vertex.user.data.UserData;

public class UserDataImpl implements UserData {

	@Override
	public UserBean getUser(String id) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserBean.class);
			criteria.add(Restrictions.eq("userId", id));
			UserBean user = (UserBean) criteria.uniqueResult();
			if ( user != null) {
				return user;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public UserBean getUser(String email, char[] password) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserBean.class);
			criteria
			.add(Restrictions.eq("email", email))
			.add(Restrictions.eq("password", String.valueOf(password)));
			UserBean users = (UserBean) criteria.uniqueResult();
			if ( users != null) {
				return users;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public List<UserBean> getUsers(int limit, Integer offset) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserBean.class);
			criteria.setMaxResults(limit).setFirstResult(offset);
			@SuppressWarnings("unchecked")
			List<UserBean> users = criteria.list();
			if ( users != null) {
				return users;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public UserBean getUsersByJobTitle(String jobTitle) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserBean.class);
			criteria.add(Restrictions.eq("jobTitle", jobTitle));
			UserBean users = (UserBean) criteria.uniqueResult();
			if ( users != null) {
				return users;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public UserBean getUsersByGender(Gender gender) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserBean.class);
			criteria.add(Restrictions.eq("gender", gender));
			UserBean users = (UserBean) criteria.uniqueResult();
			if ( users != null) {
				return users;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public UserBean createUser(UserBean user) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		
		try {
			UserBean domainUser = user;
			//TODO Should validate the user
			
			Transaction tx = session.beginTransaction();
			session.save(domainUser);
			
			tx.commit();
			return domainUser;
		} catch (HibernateException ex) {
			final String errorMessage = "A database error occured while saving the user.";
			throw new RuntimeException(errorMessage, ex);
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public UserBean getUserByEmail(String email) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserBean.class);
			criteria.add(Restrictions.eq("email", email));
			UserBean users = (UserBean) criteria.uniqueResult();
			if ( users != null) {
				return users;
			} else {
				return null;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}
}
