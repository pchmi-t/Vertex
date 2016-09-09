package com.fmi.javaee.vertex.task.event;

import java.util.List;

public interface EventDAO {

	void save(EventEntity event);

	List<EventEntity> getEventsOfUser(String userEmail, int maxEventCount);

}
