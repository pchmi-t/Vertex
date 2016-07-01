package com.fmi.javaee.vertex.user;

import java.time.Duration;

public class UserBean {

	private String userId;
	private String title;
	private String name;
	private Gender gender;

	private Duration averageTaskEcecutionTime;
	private Duration averageInvolvementResponseTime;
	private int projectsInvolvedCount;
	private long tasksExecutedCount;
	private long taskInvolvementsCount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Duration getAverageTaskEcecutionTime() {
		return averageTaskEcecutionTime;
	}

	public void setAverageTaskEcecutionTime(Duration averageTaskEcecutionTime) {
		this.averageTaskEcecutionTime = averageTaskEcecutionTime;
	}

	public Duration getAverageInvolvementResponseTime() {
		return averageInvolvementResponseTime;
	}

	public void setAverageInvolvementResponseTime(Duration averageInvolvementResponseTime) {
		this.averageInvolvementResponseTime = averageInvolvementResponseTime;
	}

	public int getProjectsInvolvedCount() {
		return projectsInvolvedCount;
	}

	public void setProjectsInvolvedCount(int projectsInvolvedCount) {
		this.projectsInvolvedCount = projectsInvolvedCount;
	}

	public long getTasksExecutedCount() {
		return tasksExecutedCount;
	}

	public void setTasksExecutedCount(long tasksExecutedCount) {
		this.tasksExecutedCount = tasksExecutedCount;
	}

	public long getTaskInvolvementsCount() {
		return taskInvolvementsCount;
	}

	public void setTaskInvolvementsCount(long taskInvolvementsCount) {
		this.taskInvolvementsCount = taskInvolvementsCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
