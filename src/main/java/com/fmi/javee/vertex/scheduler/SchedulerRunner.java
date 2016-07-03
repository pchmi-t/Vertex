package com.fmi.javee.vertex.scheduler;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulerRunner implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(SchedulerRunner.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LOG.info("Server start up. Initialize run scheduler context ...");
		try {
			runScheduler();
			LOG.info("Successfuly configure scheduler job.");
		} catch (SchedulerException ex) {
			LOG.error("An error occured configuring the scheduler "
					+ "for sending the emails.", ex);
		}
	}

	public void runScheduler() throws SchedulerException {
		JobKey jobEmailSender = new JobKey("emailSender", "group1");
		JobDetail jobEmailSenderDetail = JobBuilder.newJob(EmailSender.class)
				.withIdentity(jobEmailSender).build();

		Trigger triggerEmailSender = TriggerBuilder
				.newTrigger()
				.withIdentity("triggerEmailSender", "group1")
				.forJob(jobEmailSenderDetail)
				.startAt(new Date(System.currentTimeMillis()))
				.withSchedule(
						CronScheduleBuilder.cronSchedule("30 * * * * ?"))
				.build();

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobEmailSenderDetail, triggerEmailSender);
	}
}
