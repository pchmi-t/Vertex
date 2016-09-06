package com.fmi.javaee.vertex.event;

import java.util.Collection;

public interface EventDAO {
	
	Collection<EventEntity> getEventsOfUser(String userEmail);
	
}
