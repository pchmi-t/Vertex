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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "Projects")
@NamedQueries({ @NamedQuery(name = ProjectEntity.GET_BY_USER, query = "SELECT p FROM ProjectEntity p JOIN p.members m WHERE m.email = :mEmail") })
public class ProjectEntity {

	static final String GET_BY_USER = "getByUser";

	private String projectId;
	
	private String projectName;
	
	private String projectDescription;
	
	private Set<UserEntity> members = new HashSet<>();
	
	private Set<UserEntity> administrators = new HashSet<>();
	
	private Set<TaskEntity> tasks = new HashSet<>();
	
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "projectMembers", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	public Set<UserEntity> getMembers() {
		return members;
	}

	public void setMembers(Set<UserEntity> members) {
		this.members = members;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "projectAdmins", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	public Set<UserEntity> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<UserEntity> administrators) {
		this.administrators = administrators;
	}

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	public Set<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskEntity> tasks) {
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
