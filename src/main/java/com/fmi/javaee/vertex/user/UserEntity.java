package com.fmi.javaee.vertex.user;

import java.beans.Transient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmi.javaee.vertex.project.ProjectEntity;
import com.fmi.javaee.vertex.task.TaskEntity;

@Entity
@Table(name = "Users")
@NamedQueries({ @NamedQuery(name = UserEntity.GET_ALL_USERS, query = UserEntity.GET_ALL_USERS_QUERY),
		@NamedQuery(name = UserEntity.GET_BY_USERNAME, query = UserEntity.GET_BY_USERNAME_QUERY),
		@NamedQuery(name = UserEntity.GET_BY_EMAIL, query = UserEntity.GET_BY_EMAIL_QUERY),
		@NamedQuery(name = UserEntity.GET_BY_EMAIL_PASS, query = UserEntity.GET_BY_EMAIL_PASS_QUERY) })

public class UserEntity {

	static final String GET_ALL_USERS = "getAllUsers";

	static final String GET_ALL_USERS_QUERY = "SELECT u FROM UserEntity u";
	
	static final String GET_BY_EMAIL = "getByEmail";
	
	static final String GET_BY_EMAIL_QUERY = "SELECT u FROM UserEntity u WHERE u.email = :email";

	static final String GET_BY_USERNAME = "getByUsername";

	static final String GET_BY_USERNAME_QUERY = "SELECT u FROM UserEntity u WHERE u.username = :username";

	static final String GET_BY_EMAIL_PASS = "getByEmailAndPass";

	static final String GET_BY_EMAIL_PASS_QUERY = "SELECT u FROM UserEntity u WHERE u.email = :email AND u.password = :password";

	private String userId;

	private String jobTitle;

	private String username;

	private String fullName;

	private Gender gender;

	private String password;

	private String email;

	private Boolean isGod;

	private Collection<TaskEntity> assignedTasks = new ArrayList<>();

	private Collection<ProjectEntity> memberProjects = new ArrayList<>();

	private Collection<ProjectEntity> adminProjects = new ArrayList<>();

	private Duration averageTaskEcecutionTime;

	private Duration averageInvolvementResponseTime;

	private int projectsInvolvedCount;

	private long tasksExecutedCount;

	private long taskInvolvementsCount;

	@Column(name = "password")
	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Email
	@JsonProperty
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "isGod")
	@JsonProperty
	@ColumnDefault("false")
	public Boolean getIsGod() {
		return isGod;
	}

	public void setIsGod(Boolean isGod) {
		this.isGod = isGod;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@JsonProperty
	@Column(name = "userId", unique = true, nullable = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "jobTitle")
	@JsonProperty
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@javax.persistence.Transient
	public Duration getAverageTaskEcecutionTime() {
		return averageTaskEcecutionTime;
	}

	public void setAverageTaskEcecutionTime(Duration averageTaskEcecutionTime) {
		this.averageTaskEcecutionTime = averageTaskEcecutionTime;
	}

	@javax.persistence.Transient
	public Duration getAverageInvolvementResponseTime() {
		return averageInvolvementResponseTime;
	}

	public void setAverageInvolvementResponseTime(Duration averageInvolvementResponseTime) {
		this.averageInvolvementResponseTime = averageInvolvementResponseTime;
	}

	@javax.persistence.Transient
	public int getProjectsInvolvedCount() {
		return projectsInvolvedCount;
	}

	public void setProjectsInvolvedCount(int projectsInvolvedCount) {
		this.projectsInvolvedCount = projectsInvolvedCount;
	}

	@javax.persistence.Transient
	public long getTasksExecutedCount() {
		return tasksExecutedCount;
	}

	public void setTasksExecutedCount(long tasksExecutedCount) {
		this.tasksExecutedCount = tasksExecutedCount;
	}

	@javax.persistence.Transient
	public long getTaskInvolvementsCount() {
		return taskInvolvementsCount;
	}

	public void setTaskInvolvementsCount(long taskInvolvementsCount) {
		this.taskInvolvementsCount = taskInvolvementsCount;
	}

	@JsonProperty
	@Column(unique = true, name = "username")
	@NotBlank(message = "The username can not be blank.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	@JsonProperty
	@Column(name = "fullname")
	@NotBlank(message = "The username can not be blank.")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	@Column(name = "gender")
	@JsonProperty
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "asignee", cascade = CascadeType.ALL)
	public Collection<TaskEntity> getAssignedTasks() {
		return assignedTasks;
	}

	public void setAssignedTasks(Collection<TaskEntity> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	@ManyToMany(mappedBy = "members")
	public Collection<ProjectEntity> getMemberProjects() {
		return memberProjects;
	}

	public void setMemberProjects(Collection<ProjectEntity> memberProjects) {
		this.memberProjects = memberProjects;
	}

	@ManyToMany(mappedBy = "administrators")
	public Collection<ProjectEntity> getAdminProjects() {
		return adminProjects;
	}

	public void setAdminProjects(Collection<ProjectEntity> adminProjects) {
		this.adminProjects = adminProjects;
	}
}
