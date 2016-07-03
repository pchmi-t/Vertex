package com.fmi.javaee.vertex.event.data;

import java.util.Collection;
import java.util.Date;

import com.fmi.javaee.vertex.event.EventBean;

public interface EventData {

	int getNumberOfEvents(Date expirationTime);
	
	Collection<EventBean> getAllEventsByTime();
	
	EventBean createEvent(EventBean event);
}
