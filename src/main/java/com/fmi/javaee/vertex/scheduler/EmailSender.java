package com.fmi.javaee.vertex.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(EmailSender.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		LOG.info("Trigger ... ");
//		List<EventEntity> events = collectAllEvents();
//		Set<UserEntity> users = events.stream().map((x) -> x.getRefUser()).collect(Collectors.toSet());
//		for (Iterator<UserEntity> iterator = users.iterator(); iterator.hasNext();) {
//			UserEntity userBean = (UserEntity) iterator.next();
//			String email = userBean.getEmail();
//			String message = events.stream()
//					.filter(x -> x.getRefUser().getUserId().equals(userBean.getUserId()))
//					.map(x -> x.getDescription()).collect(Collectors.joining("\n"));
//			sendEmail(email, message);
//		}
//	}
//
//	private void sendEmail(String to, String messageText) {
//		LOG.info("Sending mail to ".concat(to));
//		String from = "vertex@admin.com";
//		String host = "localhost";
//		Properties properties = System.getProperties();  
//		properties.setProperty("mail.smtp.host", host);  
//		Session session = Session.getDefaultInstance(properties);  
//		try{  
//			MimeMessage message = new MimeMessage(session);  
//			message.setFrom(new InternetAddress(from));  
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  
//			message.setSubject("Vertex Notification Feed");  
//			message.setText(messageText);  
//			Transport.send(message);  
//			LOG.info("Message sent successfully to ".concat(to));  
//		} catch (MessagingException ex) {
//			LOG.error("Could not sent email to ".concat(to), ex);
//		}  
//	}  
//
//	private List<EventEntity> collectAllEvents() {
//		Calendar date = new GregorianCalendar();
//		date.set(Calendar.HOUR_OF_DAY, 0);
//		date.set(Calendar.MINUTE, 0);
//		date.set(Calendar.SECOND, 0);
//		date.set(Calendar.MILLISECOND, 0);
//		Date from = date.getTime();
//		date.add(Calendar.DAY_OF_MONTH, 1);
//		Date till = date.getTime();
//		return factory
//				.getEventDAO()
//				.getAllEventsByTime()
//				.stream()
//				.filter(x -> x.getCreationTime().before(till) && x.getCreationTime().after(from))
//				.collect(Collectors.toList());
//	}
}
