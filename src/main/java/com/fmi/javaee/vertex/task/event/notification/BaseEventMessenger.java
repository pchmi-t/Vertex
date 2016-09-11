package com.fmi.javaee.vertex.task.event.notification;

import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.mail.Mail;
import com.fmi.javaee.vertex.mail.MailSender;
import com.fmi.javaee.vertex.mail.MailTemplateException;
import com.fmi.javaee.vertex.mail.MailTemplateLoader;
import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.user.UserEntity;

public abstract class BaseEventMessenger implements EventMessenger {

	private static final String TASK_CHANGED_TEMPLATE = "Task has been changed by %s";

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseEventMessenger.class);

	private final MailTemplateLoader templateLoader;
	private final MailSender mailSender;

	public BaseEventMessenger(MailTemplateLoader templateLoader, MailSender mailSender) {
		this.templateLoader = templateLoader;
		this.mailSender = mailSender;
	}
	
	@Override
	public void notifySubscribers(List<UserEntity> subscribers, EventEntity event) {
		String sourceUser = event.getRefUser().getEmail();
		for (UserEntity userEntity : subscribers) {
			if (!userEntity.getEmail().equals(sourceUser)) {
				notifySubscriber(userEntity, event);
			}
		}
	}

	private void notifySubscriber(UserEntity subscriber, EventEntity event) {
		try {
			Mail notification = composeMail(subscriber, event, event.getRefTask(), event.getRefUser());
			LOGGER.info("Sending email to [{}] with subject [{}] and content: [{}]", notification.getTo(),
					notification.getSubject(), notification.getContent());
			mailSender.send(notification);
		} catch (EmailException | MailTemplateException e) {
			throw new NotificationException("Could not sent email to " + subscriber.getEmail(), e);
		}
	}

	private Mail composeMail(UserEntity subscriber, EventEntity event, TaskEntity refTask, UserEntity refUser) throws MailTemplateException {
		Mail notification = new Mail();
		notification.setTo(subscriber.getEmail());
		notification
				.setSubject(String.format(TASK_CHANGED_TEMPLATE, refUser.getFullName()));

		String mailContent = composeMailContent(subscriber, event);
		notification.setContent(mailContent);
		return notification;
	}

	private String composeMailContent(UserEntity subscriber, EventEntity event) throws MailTemplateException {
		return templateLoader.render(getMailTemplateFile(), getMailTemplateValues(subscriber, event));
	}

	protected abstract String getMailTemplateFile();
	
	protected abstract Map<String, String> getMailTemplateValues(UserEntity subscriber, EventEntity event);

}
