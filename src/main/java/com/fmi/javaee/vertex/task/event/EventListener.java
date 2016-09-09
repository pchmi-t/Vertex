package com.fmi.javaee.vertex.task.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.task.event.notification.EventMessenger;
import com.fmi.javaee.vertex.task.event.notification.EventMessengerFactory;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionDAO;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionEntity;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

public class EventListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);
	
	private final EventDAO eventDAO;
	private final SubscriptionDAO subscriptionDAO;
	private final EventMessengerFactory messengerFactory;
	
	@Inject
	public EventListener(EventDAO eventDAO, SubscriptionDAO subscriptionDAO, EventMessengerFactory messengerFactory, EventBus eventBus) {
		LOGGER.debug("Registering event listener!");
		eventBus.register(this);
		
		this.eventDAO = eventDAO;
		this.messengerFactory = messengerFactory;
		this.subscriptionDAO = subscriptionDAO;
	}
	
	@Subscribe
	public void listen(EventEntity event) {
		UserEntity refUser = event.getRefUser();
		TaskEntity refTask = event.getRefTask();
		LOGGER.error("Observed event of type [{}] from user [{}]", event.getType(), refUser.getEmail());

		if (!subscriptionDAO.isSubscribed(refUser, refTask)) {
			LOGGER.debug("Subscribing user [{}] to task with ID [{}] and title [{}]", refUser.getEmail(), refTask.getTaskId(), refTask.getTitle());
			SubscriptionEntity subscription = new SubscriptionEntity();
			subscription.setSubscribedUser(refUser);
			subscription.setSubscriptionTask(refTask);
			subscriptionDAO.create(subscription);
		}

		eventDAO.save(event);
		EventMessenger messenger = messengerFactory.create(event.getType());
		messenger.notifySubscribers(event);
	}

}
