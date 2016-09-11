package com.fmi.javaee.vertex.task;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import com.fmi.javaee.vertex.project.ProjectEntity;
import com.fmi.javaee.vertex.task.comment.CommentEntity;
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

	@Id
	@GeneratedValue(generator = "sequenceTaskId")
	@GenericGenerator(name = "sequenceTaskId", strategy = "com.fmi.javaee.vertex.task.TaskIdGenerator")
	@Column(name = "taskId")
	private String taskId;

	@Column(name = "creationTime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Column(name = "deadline")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;

	@Column(name = "modificationTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationTime;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@Column(name = "priority")
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@ManyToOne
	@JoinColumn(name = "asigneeId")
	private UserEntity asignee;

	@Column(name = "definition", length=2000)
	private String definition;

	@OneToOne
	@JoinColumn(name = "creatorId")
	private UserEntity creator;

	@Column(name = "title", updatable = false)
	private String title;

	@JoinColumn(name = "projectId")
	@ManyToOne(cascade = CascadeType.MERGE)
	private ProjectEntity project;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "subscriptionTask")
	private Set<SubscriptionEntity> subscriptions = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "commentedTask")
	private Set<CommentEntity> comments = new HashSet<>();

	public String getTaskId() {
		return taskId;
	}

	public Set<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(Set<CommentEntity> comments) {
		this.comments = comments;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date currentDate) {
		this.creationTime = currentDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date currentDate) {
		this.modificationTime = currentDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public UserEntity getAsignee() {
		return asignee;
	}

	public void setAsignee(UserEntity asignee) {
		this.asignee = asignee;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public Set<SubscriptionEntity> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<SubscriptionEntity> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public void addComment(CommentEntity commentEntity) {
		comments.add(commentEntity);
	}

}
