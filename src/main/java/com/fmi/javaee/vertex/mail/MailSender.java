package com.fmi.javaee.vertex.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class MailSender {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);
	
	private final MailConfig mailConfig;
	
	@Inject
	public MailSender(MailConfig mailConfig) {
		this.mailConfig = mailConfig;
	}
	
	public void send(Mail mail) throws EmailException {
		LOGGER.debug("Sending mail to [{}] with subject [{}]", mail.getTo(), mail.getSubject());

		HtmlEmail email = new HtmlEmail();
		email.setFrom(mailConfig.getUser(), mailConfig.getUsername());
		email.setHostName(mailConfig.getHost());
		email.setSmtpPort(mailConfig.getSmtpPort());
		email.setAuthentication(mailConfig.getUser(), mailConfig.getPassword());
		email.setTLS(true);
		email.setSSL(true);
		
		email.addTo(mail.getTo());
		email.setSubject(mail.getSubject());
		email.setHtmlMsg(mail.getContent());
		email.send();
		
		LOGGER.debug("Successfully sent email to [{}]", mail.getTo());
	}
	
}
