package com.fmi.javaee.vertex.task.event.notification;

import java.util.HashMap;
import java.util.Map;

import com.fmi.javaee.vertex.mail.MailSender;
import com.fmi.javaee.vertex.mail.MailTemplateLoader;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;

public class DefaultMessenger extends BaseEventMessenger {

	private static final String DEFAULT_MAIL_TEMPLATE = "default_task_change_template.mail";

	@Inject
	public DefaultMessenger(MailSender mailSender, MailTemplateLoader templateLoader) {
		super(templateLoader, mailSender);
	}

	@Override
	protected String getMailTemplateFile() {
		return DEFAULT_MAIL_TEMPLATE;
	}

	@Override
	protected Map<String, String> getMailTemplateValues(UserEntity subscriber, EventEntity event) {
		Map<String, String> templateContext = new HashMap<>();
		templateContext.put("othername", event.getRefUser().getFullName());
		templateContext.put("name", subscriber.getFullName());
		templateContext.put("taskid", event.getRefTask().getTaskId());
		templateContext.put("before", event.getBefore());
		templateContext.put("after", event.getAfter());
		return templateContext;
	}

}
