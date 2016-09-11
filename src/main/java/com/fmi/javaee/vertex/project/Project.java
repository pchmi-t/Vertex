package com.fmi.javaee.vertex.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fmi.javaee.vertex.task.Task;
import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.User;
import com.fmi.javaee.vertex.user.UserEntity;

public class Project {

	private final long projectId;
	private final String projectName;
	private final String projectDescription;
	private final Date creationTime;
	private final Set<User> members = new HashSet<>();
	private final Set<User> administrators = new HashSet<>();
	private final Set<Task> tasks = new HashSet<>();

	public Project(ProjectEntity entity) {
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

		for (TaskEntity task : entity.getTasks()) {
			tasks.add(new Task(task));
		}
	}

	public long getProjectId() {
		return projectId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Set<User> getMembers() {
		return members;
	}

	public Set<User> getAdministrators() {
		return administrators;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

}
