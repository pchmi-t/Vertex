package com.fmi.javaee.vertex.task.event.subscription;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class SubscriptionDAOImpl implements SubscriptionDAO {
	
	private final Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public SubscriptionDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	@Transactional
	public void create(SubscriptionEntity subscription) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(subscription);
	}

	@Override
	@Transactional
	public boolean isSubscribed(UserEntity user, TaskEntity task) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<SubscriptionEntity> query = entityManager.createNamedQuery(SubscriptionEntity.IS_SUBSCRIBED_QUERY, SubscriptionEntity.class);
		query.setParameter("user", user);
		query.setParameter("task", task);
		
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public List<UserEntity> getSubscribers(TaskEntity refTask) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<UserEntity> query = entityManager.createNamedQuery(SubscriptionEntity.GET_SUBSCRIBED_QUERY, UserEntity.class);
		query.setParameter("task", refTask);
		return query.getResultList();
	}

}
