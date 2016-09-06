package com.fmi.javaee.vertex.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

class UserDAOImpl implements UserDAO {

	private final EntityManager entityManager;

	@Inject
	UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UserEntity getUser(String id) {
		return entityManager.find(UserEntity.class, id);
	}

	@Override
	public UserEntity getUser(String email, char[] password) {
		TypedQuery<UserEntity> getByUserNameQuery = entityManager.createNamedQuery(UserEntity.GET_BY_EMAIL_PASS,
				UserEntity.class);
		getByUserNameQuery.setParameter("email", email);
		getByUserNameQuery.setParameter("password", new String(password));
		try {
			return getByUserNameQuery.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<UserEntity> getUsers(int limit, Integer offset) {
		return entityManager.createNamedQuery(UserEntity.GET_ALL_USERS, UserEntity.class).setFirstResult(offset)
				.setMaxResults(limit).getResultList();
	}

	@Override
	@Transactional
	public UserEntity createUser(UserEntity user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		TypedQuery<UserEntity> getByUserNameQuery = entityManager.createNamedQuery(UserEntity.GET_BY_EMAIL,
				UserEntity.class);
		getByUserNameQuery.setParameter("email", email);
		try {
			return getByUserNameQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		TypedQuery<UserEntity> getByUserNameQuery = entityManager.createNamedQuery(UserEntity.GET_BY_USERNAME,
				UserEntity.class);
		getByUserNameQuery.setParameter("username", username);
		return getByUserNameQuery.getSingleResult();
	}
}
