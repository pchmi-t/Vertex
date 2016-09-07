package com.fmi.javaee.vertex.task.event.notification;

import com.fmi.javaee.vertex.mail.Mail;
import com.fmi.javaee.vertex.mail.MailSender;
import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionDAO;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;

public class DefaultMessenger extends BaseEventMessenger {

	@Inject
	public DefaultMessenger(SubscriptionDAO subscriptionDAO, MailSender mailSender) {
		super(subscriptionDAO, mailSender);
	}

	@Override
	protected Mail composeMail(UserEntity subscriber, EventEntity event) {
		TaskEntity refTask = event.getRefTask();
		UserEntity refUser = event.getRefUser();

		Mail mail = new Mail();
		mail.setTo(refUser.getEmail());
		mail.setSubject(String.format("Task %s was motified by %s", refTask.getTaskId(), refUser.getUsername()));

		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("Dear ").append(subscriber.getUsername()).append("\n\n");
		contentBuilder.append("A task for which you are subscribed was modified by ").append(refUser.getUsername())
				.append("\n");
		contentBuilder.append("Previous state: ").append(event.getBefore()).append("\n");
		contentBuilder.append("New state: ").append(event.getAfter()).append("\n\n");
		contentBuilder.append("Best regards,").append("\n").append("Vertex Team");

		mail.setContent(contentBuilder.toString());
		return mail;
	}

}
