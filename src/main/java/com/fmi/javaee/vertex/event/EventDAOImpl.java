package com.fmi.javaee.vertex.event;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;

class EventDAOImpl implements EventDAO {

	private final EntityManager entityManager;

	@Inject
	EventDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Collection<EventEntity> getEventsOfUser(String userEmail) {
		TypedQuery<EventEntity> getByUserQuery = entityManager.createNamedQuery(EventEntity.GET_BY_USER,  EventEntity.class);
		getByUserQuery.setParameter("email", userEmail);
		return getByUserQuery.getResultList();
	}
}
