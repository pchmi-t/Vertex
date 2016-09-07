package com.fmi.javaee.vertex.task.event.notification;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.fmi.javaee.vertex.mail.Mail;
import com.fmi.javaee.vertex.mail.MailSender;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionDAO;
import com.fmi.javaee.vertex.user.UserEntity;

public abstract class BaseEventMessenger implements EventMessenger {

	private final SubscriptionDAO subscriptionDAO;
	private final MailSender mailSender;

	public BaseEventMessenger(SubscriptionDAO subscriptionDAO, MailSender mailSender) {
		this.subscriptionDAO = subscriptionDAO;
		this.mailSender = mailSender;
	}

	@Override
	public void notifySubscribers(EventEntity event) {
		String sourceUser = event.getRefUser().getEmail();
		
		List<UserEntity> subscribers = subscriptionDAO.getSubscribers(event.getRefTask());
		for (UserEntity userEntity : subscribers) {
			if (!userEntity.getEmail().equals(sourceUser)) {
				notifySubscriber(userEntity, event);
			}
		}

	}

	private void notifySubscriber(UserEntity subscriber,EventEntity event) {
		Mail notification = composeMail(subscriber, event);
		try {
			mailSender.send(notification);
		} catch (EmailException e) {
			throw new NotificationException("Could not sent email to " + event.getRefUser().getEmail(), e);
		}
	}

	protected abstract Mail composeMail(UserEntity subscriber, EventEntity event);

}
