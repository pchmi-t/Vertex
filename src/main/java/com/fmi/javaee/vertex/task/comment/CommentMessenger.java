package com.fmi.javaee.vertex.task.comment;

import java.util.HashMap;
import java.util.Map;

import com.fmi.javaee.vertex.mail.MailSender;
import com.fmi.javaee.vertex.mail.MailTemplateLoader;
import com.fmi.javaee.vertex.task.event.EventEntity;
import com.fmi.javaee.vertex.task.event.notification.BaseEventMessenger;
import com.fmi.javaee.vertex.user.UserEntity;
import com.google.inject.Inject;

public class CommentMessenger extends BaseEventMessenger {

	private static final String COMMENT_MAIL_TEMPLATE = "comment_mail_template.mail";

	@Inject
	public CommentMessenger(MailSender mailSender, MailTemplateLoader templateLoader) {
		super(templateLoader, mailSender);
	}

	@Override
	protected String getMailTemplateFile() {
		return COMMENT_MAIL_TEMPLATE;
	}

	@Override
	protected Map<String, String> getMailTemplateValues(UserEntity subscriber, EventEntity event) {
		Map<String, String> templateContext = new HashMap<>();
		templateContext.put("othername", event.getRefUser().getFullName());
		templateContext.put("name", subscriber.getFullName());
		templateContext.put("taskid", event.getRefTask().getTaskId());
		templateContext.put("comment", event.getAfter());
		return templateContext;
	}

}
