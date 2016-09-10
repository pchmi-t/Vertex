package com.fmi.javaee.vertex.task.event.notification;

import java.util.HashMap;
import java.util.Map;

import com.fmi.javaee.vertex.mail.MailSender;
import com.fmi.javaee.vertex.mail.MailTemplateLoader;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;

public class PriorityChangeMessenger extends BaseEventMessenger {

	private static final String PRIO_CHANGE_MAIL_TEMPLATE = "priority_change_mail_template.mail";

	@Inject
	public PriorityChangeMessenger(MailSender mailSender, MailTemplateLoader templateLoader) {
		super(templateLoader, mailSender);
	}
	
	@Override
	protected String getMailTemplateFile() {
		return PRIO_CHANGE_MAIL_TEMPLATE;
	}

	@Override
	protected Map<String, String> getMailTemplateValues(UserEntity subscriber, EventEntity event) {
		Map<String, String> templateContext = new HashMap<>();
		templateContext.put("othername", event.getRefUser().getFullName());
		templateContext.put("name", subscriber.getFullName());
		templateContext.put("taskid", event.getRefTask().getTaskId());
		templateContext.put("newprio", event.getAfter());
		return templateContext;
	}

}
