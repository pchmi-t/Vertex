package com.fmi.javaee.vertex.event;

import java.util.Collection;

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
	public Collection<EventEntity> getEventsOfUser(String userEmail) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<EventEntity> getByUserQuery = entityManager.createNamedQuery(EventEntity.GET_BY_USER,  EventEntity.class);
		getByUserQuery.setParameter("email", userEmail);
		return getByUserQuery.getResultList();
	}
}
