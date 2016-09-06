package com.fmi.javaee.vertex.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.User;
import com.fmi.javaee.vertex.user.UserEntity;

public class ProjectBean {

	private String projectId;
	private String projectName;
	private String projectDescription;
	private Date creationTime;
	private Set<User> members = new HashSet<>();
	private Set<User> administrators = new HashSet<>();
	private Set<TaskEntity> tasks = new HashSet<>();

	public ProjectBean() {
	}

	public ProjectBean(ProjectEntity entity) {
		this.projectId = entity.getProjectId();
		this.projectName = entity.getProjectName();
		this.projectDescription = entity.getProjectDescription();
		this.creationTime = entity.getCreationTime();
		for (UserEntity adminEntity : entity.getAdministrators()) {
			this.administrators.add(new User(adminEntity));
		}
		
		for (UserEntity memberEntity : entity.getMembers()) {
			this.members.add(new User(memberEntity));
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

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public Set<User> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<User> administrators) {
		this.administrators = administrators;
	}

	public Set<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskEntity> tasks) {
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
