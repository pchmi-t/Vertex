package com.fmi.javaee.vertex.task.comment;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class CommentDAOImpl implements CommentDAO {
	
	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	public CommentDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}
	
	@Override
	@Transactional
	public void save(CommentEntity comment) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(comment);
	}
}
