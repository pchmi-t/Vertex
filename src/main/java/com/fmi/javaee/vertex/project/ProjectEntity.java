package com.fmi.javaee.vertex.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "Projects")
@NamedQueries({
		@NamedQuery(name = ProjectEntity.GET_BY_USER, query = "SELECT p FROM ProjectEntity p JOIN p.members m WHERE m.email = :mEmail") })
public class ProjectEntity {

	static final String GET_BY_USER = "getByUser";

	@Id
	@GeneratedValue
	private long projectId;

	@Column(name = "projectName")
	private String projectName;

	@Column(name = "projectDescription", length=2000)
	private String projectDescription;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "projectMembers", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	private Set<UserEntity> members = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "projectAdmins", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	private Set<UserEntity> administrators = new HashSet<>();

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	private Set<TaskEntity> tasks = new HashSet<>();

	@Column(name = "creationTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	public long getProjectId() {
		return projectId;
	}

	public Set<UserEntity> getMembers() {
		return members;
	}

	public void setMembers(Set<UserEntity> members) {
		this.members = members;
	}

	public Set<UserEntity> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<UserEntity> administrators) {
		this.administrators = administrators;
	}

	public Set<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskEntity> tasks) {
		this.tasks = tasks;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
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
