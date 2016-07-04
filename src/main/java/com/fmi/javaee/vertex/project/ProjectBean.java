package com.fmi.javaee.vertex.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.user.UserBean;
import com.fmi.javaee.vertex.user.UserEntity;

public class ProjectBean {

	private String projectId;
	private String projectName;
	private String projectDescription;
	private Date creationTime;
	private Set<UserBean> members = new HashSet<>();
	private Set<UserBean> administrators = new HashSet<>();
	private Set<TaskBean> tasks = new HashSet<>();

	public ProjectBean() {
	}

	public ProjectBean(ProjectEntity entity) {
		this.projectId = entity.getProjectId();
		this.projectName = entity.getProjectName();
		this.projectDescription = entity.getProjectDescription();
		this.creationTime = entity.getCreationTime();
		for (UserEntity adminEntity : entity.getAdministrators()) {
			this.administrators.add(new UserBean(adminEntity));
		}
		
		for (UserEntity memberEntity : entity.getMembers()) {
			this.members.add(new UserBean(memberEntity));
		}
		this.tasks = entity.getTasks();

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

	public Set<UserBean> getMembers() {
		return members;
	}

	public void setMembers(Set<UserBean> members) {
		this.members = members;
	}

	public Set<UserBean> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<UserBean> administrators) {
		this.administrators = administrators;
	}

	public Set<TaskBean> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskBean> tasks) {
		this.tasks = tasks;
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
