package com.fmi.javaee.vertex.user.data.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fmi.javaee.vertex.criteria.BaseCriteria;
import com.fmi.javaee.vertex.session.SessionFactoryData;
import com.fmi.javaee.vertex.user.Gender;
import com.fmi.javaee.vertex.user.UserBean;
import com.fmi.javaee.vertex.user.data.UserCriterion;
import com.fmi.javaee.vertex.user.data.UserData;

public class UserDataImpl implements UserData {

	@Override
	public UserBean getUser(Long id) {
		UserCriterion criterion = getUserCriterion();
		Session session = SessionFactoryData.getSessionFactory().openSession();
		criterion.id(id);
		try {
			Criteria criteria = ((UserCriterionImpl)(criterion)).
					getCriteria(session, UserBean.class);
			@SuppressWarnings("unchecked")
			List<UserBean> users = criteria.list();
			if ( users != null && !users.isEmpty()) {
				return users.get(0);
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
	public UserBean getUser(UserCriterion criterion) {
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

	@Override
	public UserCriterion getUserCriterion() {
		return new UserCriterionImpl(UserBean.class);
	}
	
	private final class UserCriterionImpl extends BaseCriteria implements UserCriterion {

		private static final long serialVersionUID = 1L;

		public UserCriterionImpl(Class<?> bean) {
			super(bean);
		}

		@Override
		public UserCriterion id(Long id) {
			and(Restrictions.eq("id", id));
			return this;
		}

		@Override
		public UserCriterion email(String email) {
			and(Restrictions.eq("email", email));
			return this;
		}

		@Override
		public UserCriterion jobTitle(String jobTitle) {
			and(Restrictions.eq("jobTitle", jobTitle));
			return this;
		}

		@Override
		public UserCriterion isGod(Boolean isGod) {
			and(Restrictions.eq("isGod", isGod));
			return this;
		}

		@Override
		public UserCriterion gender(Gender gender) {
			and(Restrictions.eq("gender", gender));
			return this;
		}
	}
}
