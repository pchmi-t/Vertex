package com.fmi.javaee.vertex.user;

import java.beans.Transient;
import java.io.Serializable;
import java.time.Duration;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="Users")
@Entity
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Persisted properties
	private String userId;
	private String jobTitle;
	private String name;
	private Gender gender;
	private String password; //will served as passphrase
	private String email;
	private Boolean isGod;
	//TODO implement certRef
	
	private Duration averageTaskEcecutionTime;


	private Duration averageInvolvementResponseTime;
	private int projectsInvolvedCount;
	private long tasksExecutedCount;
	private long taskInvolvementsCount;
	
	@Column(name="password")
	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="email")
	@JsonProperty
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="isGod")
	@JsonProperty
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
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="jobTitle")
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

	@Column(name="name")
	@NotBlank(message="The username can not be blank.")
	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	@Column(name="gender")
	@JsonProperty
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
