package com.fmi.javaee.vertex.task.event.notification;

import com.fmi.javaee.vertex.task.event.EventEntity;

public interface EventMessenger {

	public void notifySubscribers(EventEntity event);
	
}
