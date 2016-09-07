package com.fmi.javaee.vertex.task.event.subscription;

import java.util.List;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

public interface SubscriptionDAO {
	
	public void create(SubscriptionEntity subscription);
	
	public boolean isSubscribed(UserEntity user, TaskEntity task);

	public List<UserEntity> getSubscribers(TaskEntity refTask);

}
