package com.fmi.javaee.vertex.mail;

public class MailConfig {

	private String user;
	private String username;
	private String password;
	private String host;
	private int smtpPort;

	public MailConfig(String user, String username, String password, String host, int smtpPort) {
		this.user = user;
		this.username = username;
		this.password = password;
		this.host = host;
		this.smtpPort = smtpPort;
	}

	public MailConfig() {
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getHost() {
		return host;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

}
