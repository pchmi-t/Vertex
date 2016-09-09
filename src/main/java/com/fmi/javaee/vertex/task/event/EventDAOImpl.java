package com.fmi.javaee.vertex.task.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;

class EventDAOImpl implements EventDAO {

	private final Provider<EntityManager> entityManagerProvider;

	@Inject
	EventDAOImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
	}
	
	@Override
	public void save(EventEntity event) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(event);
	}

	@Override
	public List<EventEntity> getEventsOfUser(String userEmail,  int maxEventCount) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<EventEntity> getByUserQuery = entityManager.createNamedQuery(EventEntity.GET_BY_USER,  EventEntity.class);
		getByUserQuery.setParameter("email", userEmail).setMaxResults(maxEventCount);
		return getByUserQuery.getResultList();
	}

}
