package com.fmi.javaee.vertex.task;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmi.javaee.vertex.project.ProjectEntity;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "tasks")
@NamedQueries({
		@NamedQuery(name = TaskEntity.GET_BY_ASIGNEE, query = "SELECT t FROM TaskEntity t WHERE t.asignee = :asignee"),
		@NamedQuery(name = TaskEntity.GET_BY_CREATOR, query = "SELECT t FROM TaskEntity t WHERE t.creator = :creator") })
public class TaskEntity {

	static final String GET_BY_ASIGNEE = "getByAsignee";

	static final String GET_BY_CREATOR = "getByCreator";

	private String taskId;

	private Date creationTime;

	private Date modificationTime;

	private TaskStatus status;

	private Priority priority;

	private UserEntity asignee;

	private String definition;

	private UserEntity creator;

	private UserEntity lastModificator;

	private String title;

	private ProjectEntity project;

	private List<SubscriptionEntity> subscriptions;

	// private Project project;

	@Id
	@GeneratedValue(generator = "sequence_task_id")  
	@GenericGenerator(name = "sequence_task_id", strategy = "com.fmi.javaee.vertex.task.TaskIdGenerator")
	@JsonProperty
	@Column(name = "taskId", unique = true, nullable = false)
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "creationTime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date currentDate) {
		this.creationTime = currentDate;
	}

	@Column(name = "modificationTime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty
	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date currentDate) {
		this.modificationTime = currentDate;
	}

	@Column(name = "status")
	@JsonProperty
	@Enumerated(EnumType.STRING)
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asignee_id")
	public UserEntity getAsignee() {
		return asignee;
	}

	public void setAsignee(UserEntity asignee) {
		this.asignee = asignee;
	}

	@Column(name = "definition")
	@JsonProperty
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "creator_id")
	@JsonProperty
	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}

	@JsonProperty
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "last_modificator_id")
	public UserEntity getLastModificator() {
		return lastModificator;
	}

	public void setLastModificator(UserEntity lastModificator) {
		this.lastModificator = lastModificator;
	}

	@JsonProperty
	@Column(name = "priority")
	@Enumerated(EnumType.STRING)
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Column(name = "title", updatable = false)
	@NotBlank(message = "The task title can not be null or empty.")
	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subscriptionTask")
	public List<SubscriptionEntity> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionEntity> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
