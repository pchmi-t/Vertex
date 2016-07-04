package com.fmi.javaee.vertex.project;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "Projects")
public class ProjectEntity implements Serializable {

	private static final long serialVersionUID = 2718353048410816625L;

	private String projectId;
	private String projectName;
	private String projectDescription;
	private Set<UserEntity> members = new HashSet<>();
	private Set<UserEntity> administrators = new HashSet<>();
	private Set<TaskBean> tasks = new HashSet<>();
	private Date creationTime;

	@Id
	@JsonProperty
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "projectId", unique = true, nullable = false)
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@ManyToMany
	@JoinTable(name = "projectMembers", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "email"))
	public Set<UserEntity> getMembers() {
		return members;
	}

	public void setMembers(Set<UserEntity> members) {
		this.members = members;
	}

	@ManyToMany
	@JoinTable(name = "projectAdmins", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "email"))
	public Set<UserEntity> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<UserEntity> administrators) {
		this.administrators = administrators;
	}

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	public Set<TaskBean> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskBean> tasks) {
		this.tasks = tasks;
	}

	@JsonProperty
	@Column(name = "creationTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@JsonProperty
	@Column(name = "projectName")
	@NotBlank(message = "The name of the project can not be blank.")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@JsonProperty
	@Column(name = "projectDescription")
	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

}
