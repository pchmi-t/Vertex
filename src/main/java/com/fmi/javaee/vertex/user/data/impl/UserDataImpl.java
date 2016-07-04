package com.fmi.javaee.vertex.user.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fmi.javaee.vertex.session.SessionFactoryData;
import com.fmi.javaee.vertex.user.Gender;
import com.fmi.javaee.vertex.user.UserEntity;
import com.fmi.javaee.vertex.user.data.UserData;

public class UserDataImpl implements UserData {

	@Override
	public UserEntity getUser(String id) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria.add(Restrictions.eq("userId", id));
			UserEntity user = (UserEntity) criteria.uniqueResult();
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
	public UserEntity getUser(String email, char[] password) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria
			.add(Restrictions.eq("email", email))
			.add(Restrictions.eq("password", String.valueOf(password)));
			UserEntity users = (UserEntity) criteria.uniqueResult();
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
	public List<UserEntity> getUsers(int limit, Integer offset) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria.setMaxResults(limit).setFirstResult(offset);
			@SuppressWarnings("unchecked")
			List<UserEntity> users = criteria.list();
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
	public UserEntity getUsersByJobTitle(String jobTitle) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria.add(Restrictions.eq("jobTitle", jobTitle));
			UserEntity users = (UserEntity) criteria.uniqueResult();
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
	public UserEntity getUsersByGender(Gender gender) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria.add(Restrictions.eq("gender", gender));
			UserEntity users = (UserEntity) criteria.uniqueResult();
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
	public UserEntity createUser(UserEntity user) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		
		try {
			UserEntity domainUser = user;
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
	public UserEntity getUserByEmail(String email) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria.add(Restrictions.eq("email", email));
			UserEntity users = (UserEntity) criteria.uniqueResult();
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
	public UserEntity getUserByUsername(String username) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserEntity.class);
			criteria.add(Restrictions.eq("username", username));
			UserEntity users = (UserEntity) criteria.uniqueResult();
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
