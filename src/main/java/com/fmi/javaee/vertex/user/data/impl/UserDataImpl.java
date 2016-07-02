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
	public UserBean getUser(Long id) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserBean.class);
			criteria.add(Restrictions.eq("userId", id));
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
	public UserBean getUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean getUsers(int limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean getUsersByJobTitle(String jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean getUsersByGender(Gender gender) {
		// TODO Auto-generated method stub
		return null;
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
}
