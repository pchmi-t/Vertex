package com.fmi.javaee.vertex.task.comment;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class CommentDAOImpl implements CommentDAO {
	
	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	public CommentDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}
	
	@Override
	public void save(CommentEntity comment) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.getTransaction().begin();
		entityManager.persist(comment);
		entityManager.getTransaction().commit();
	}
}
