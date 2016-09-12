package com.fmi.javaee.vertex.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fmi.javaee.vertex.security.VertexEncryptor;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
class UserDAOImpl implements UserDAO {

	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	UserDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	@Transactional
	public UserEntity getUser(long id) {
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.find(UserEntity.class, id);
	}

	@Override
	@Transactional
	public UserEntity getUser(String email, char[] password) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<UserEntity> getByUserNameQuery = entityManager.createNamedQuery(UserEntity.GET_BY_EMAIL_PASS,
				UserEntity.class);
		getByUserNameQuery.setParameter("email", email);
		String encryptedPassword = null;
		try {
			encryptedPassword = VertexEncryptor.encrypt(new String(password));
		} catch (Exception e1) {
			return null;
		}
		getByUserNameQuery.setParameter("password", encryptedPassword);
		try {
			return getByUserNameQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<UserEntity> getUsers(int limit, Integer offset) {
		EntityManager entityManager = entityManagerProvider.get();
		return entityManager.createNamedQuery(UserEntity.GET_ALL_USERS, UserEntity.class).setFirstResult(offset)
				.setMaxResults(limit).getResultList();
	}

	@Override
	@Transactional
	public UserEntity createUser(UserEntity user) {
		EntityManager entityManager = entityManagerProvider.get();
		try {
			user.setPassword(VertexEncryptor.encrypt(user.getPassword()));
		} catch (Exception e) {
			return null;
		}
		entityManager.persist(user);
		return user;
	}

	@Override
	@Transactional
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
	@Transactional
	public UserEntity getUserByUsername(String username) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<UserEntity> getByUserNameQuery = entityManager.createNamedQuery(UserEntity.GET_BY_USERNAME,
				UserEntity.class);
		getByUserNameQuery.setParameter("username", username);
		return getByUserNameQuery.getSingleResult();
	}
}
