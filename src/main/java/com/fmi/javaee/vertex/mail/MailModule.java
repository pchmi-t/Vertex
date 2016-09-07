package com.fmi.javaee.vertex.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class MailModule extends AbstractModule {

	private static final String MAIL_CREDENTIALS_FILE = "mail.properties";
	private static final String MAIL_USER_PROPERTY = "mail.user";
	private static final String MAIL_USERNAME_PROPERTY = "mail.username";
	private static final String MAIL_PASS_PROPERTY = "mail.pass";
	private static final String MAIL_HOST_PROPERTY = "mail.host";
	private static final String MAIL_SMTP_PORT_PROPERTY = "mail.smtpPort";

	@Override
	protected void configure() {
		bind(MailSender.class);
	}

	@Provides
	@Singleton
	public MailConfig provideMailCredentials() throws IOException {
		try (InputStream is = MailConfig.class.getClassLoader().getResourceAsStream(MAIL_CREDENTIALS_FILE)) {
			if (is != null) {
				Properties mailProperties = new Properties();
				mailProperties.load(is);
				
				String user = mailProperties.getProperty(MAIL_USER_PROPERTY);
				String username = mailProperties.getProperty(MAIL_USERNAME_PROPERTY);
				String pass = mailProperties.getProperty(MAIL_PASS_PROPERTY);
				String host = mailProperties.getProperty(MAIL_HOST_PROPERTY);
				int smtpPort = Integer.valueOf(mailProperties.getProperty(MAIL_SMTP_PORT_PROPERTY));

				return new MailConfig(user, username, pass, host, smtpPort);
			} else {
				throw new IllegalStateException("Missing required mail properties file: " + MAIL_CREDENTIALS_FILE);
			}
		}
	}
}
