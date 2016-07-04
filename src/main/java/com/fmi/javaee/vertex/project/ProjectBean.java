package com.fmi.javaee.vertex.project;

import java.util.Date;

public class ProjectBean {

	private String projectId;
	private String projectName;
	private String projectDescription;
	private Date creationTime;
	private int taskCount;
	private int membersCount;

	public ProjectBean() {
	}

	public ProjectBean(ProjectEntity entity) {
		this.projectId = entity.getProjectId();
		this.projectName = entity.getProjectName();
		this.projectDescription = entity.getProjectDescription();
		this.creationTime = entity.getCreationTime();
		this.taskCount = entity.getTasks().size();
		this.membersCount = entity.getMembers().size();

	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public int getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(int membersCount) {
		this.membersCount = membersCount;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

}
