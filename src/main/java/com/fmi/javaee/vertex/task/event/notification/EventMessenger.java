package com.fmi.javaee.vertex.task.event.notification;

import java.util.List;

import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.user.UserEntity;

public interface EventMessenger {

	public void notifySubscribers(List<UserEntity> subscribers, EventEntity event);
	
}
