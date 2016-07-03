package com.fmi.javee.vertex.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmi.javaee.vertex.event.EventBean;
import com.fmi.javaee.vertex.factory.Factory;
import com.fmi.javaee.vertex.user.UserBean;

public class EmailSender implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(EmailSender.class);

	private Factory factory = Factory.getInstance();

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LOG.info("Trigger ... ");
		List<EventBean> events = collectAllEvents();
		Set<UserBean> users = events.stream().map((x) -> x.getRefUser()).collect(Collectors.toSet());
		for (Iterator<UserBean> iterator = users.iterator(); iterator.hasNext();) {
			UserBean userBean = (UserBean) iterator.next();
			String email = userBean.getEmail();
			String message = events.stream()
					.filter(x -> x.getRefUser().getUserId().equals(userBean.getUserId()))
					.map(x -> x.getDescription()).collect(Collectors.joining("\n"));
			//TODO send an email
		}
	}

	private List<EventBean> collectAllEvents() {
		// today    
		Calendar date = new GregorianCalendar();
		// reset hour, minutes, seconds and millis
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		Date from = date.getTime();
		// next day
		date.add(Calendar.DAY_OF_MONTH, 1);
		Date till = date.getTime();
		return factory
				.getEventData()
				.getAllEventsByTime()
				.stream()
				.filter(x -> x.getCreationTime().before(till) && x.getCreationTime().after(from))
				.collect(Collectors.toList());
	}
}
