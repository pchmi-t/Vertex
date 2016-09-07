package com.fmi.javaee.vertex.task.event.notification;

import com.fmi.javaee.vertex.mail.Mail;
import com.fmi.javaee.vertex.mail.MailSender;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionDAO;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;

public class AssignmentMessenger extends BaseEventMessenger {

	@Inject
	public AssignmentMessenger(SubscriptionDAO subscriptionDAO, MailSender mailSender) {
		super(subscriptionDAO, mailSender);
	}

	@Override
	protected Mail composeMail(UserEntity subscriber, EventEntity event) {
		// TODO Auto-generated method stub
		return null;
	}



}
