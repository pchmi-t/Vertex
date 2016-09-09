package com.fmi.javaee.vertex.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;

class UserDAOImpl implements UserDAO {

	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	UserDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	public UserEntity getUser(String id) {
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.find(UserEntity.class, id);
	}

	@Override
	public UserEntity getUser(String email, char[] password) {
		EntityManager entityManager = entityManagerProvider.get();
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
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.createNamedQuery(UserEntity.GET_ALL_USERS, UserEntity.class).setFirstResult(offset)
				.setMaxResults(limit).getResultList();
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		EntityManager entityManager = entityManagerProvider.get();
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
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<UserEntity> getByUserNameQuery = entityManager.createNamedQuery(UserEntity.GET_BY_USERNAME,
				UserEntity.class);
		getByUserNameQuery.setParameter("username", username);
		return getByUserNameQuery.getSingleResult();
	}
}
