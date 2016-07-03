package com.fmi.javaee.vertex.event.data.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fmi.javaee.vertex.event.EventBean;
import com.fmi.javaee.vertex.event.data.EventData;
import com.fmi.javaee.vertex.session.SessionFactoryData;

public class EventDataImpl implements EventData {

	@Override
	public int getNumberOfEvents(Date expirationTime) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(EventBean.class);
			@SuppressWarnings("unchecked")
			List<EventBean> event = criteria.list();
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
	public Collection<EventBean> getAllEventsByTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventBean createEvent(EventBean event) {
		Session session = SessionFactoryData.getSessionFactory().openSession();
		try {
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
}
