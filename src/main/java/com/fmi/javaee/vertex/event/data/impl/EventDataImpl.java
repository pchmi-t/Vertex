package com.fmi.javaee.vertex.event.data.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.fmi.javaee.vertex.event.EventEntity;
import com.fmi.javaee.vertex.event.data.EventDAO;
import com.fmi.javaee.vertex.session.SessionFactoryData;

public class EventDataImpl implements EventDAO {

	@Override
	public int getNumberOfEvents(Date expirationTime) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(EventEntity.class);
			@SuppressWarnings("unchecked")
			List<EventEntity> event = criteria.list();
			if ( event != null) {
				return event.size();
			} else {
				return 0;
			}
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public Collection<EventEntity> getAllEventsByTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventEntity createEvent(EventEntity event) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			event.setCreationTime(new Date());
			Transaction tx = session.beginTransaction();
			session.save(event);
			tx.commit();
			return event;
		} catch (HibernateException ex) {
			final String errorMessage = "A database error occured while saving the event.";
			throw new RuntimeException(errorMessage, ex);
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}

	@Override
	public Collection<EventEntity> getEventsOfUser(String userEmail) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			Query<EventEntity> query = session.createNamedQuery("getEventsByUser", EventEntity.class);
			List<EventEntity> resultList = query.setParameter("email", userEmail).getResultList();
			return resultList;
		} finally {
			SessionFactoryData.closeSession(session);
		}
	}
}
