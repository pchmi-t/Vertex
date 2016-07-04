package com.fmi.javaee.vertex.event.data;

import java.util.Collection;
import java.util.Date;

import com.fmi.javaee.vertex.event.EventEntity;

public interface EventDAO {

	int getNumberOfEvents(Date expirationTime);
	
	Collection<EventEntity> getAllEventsByTime();
	
	Collection<EventEntity> getEventsOfUser(String userEmail);
	
	EventEntity createEvent(EventEntity event);
}
