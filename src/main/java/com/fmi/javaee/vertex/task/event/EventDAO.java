package com.fmi.javaee.vertex.task.event;

import java.util.Collection;

public interface EventDAO {

	void save(EventEntity event);

	Collection<EventEntity> getEventsOfUser(String userEmail);

}
