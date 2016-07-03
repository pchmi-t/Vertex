package com.fmi.javaee.vertex.task;

import java.util.Observable;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmi.javaee.vertex.task.monitoring.Component;
import com.fmi.javaee.vertex.user.UserBean;

@Table(name="tasks")
@Entity
public class TaskBean extends Observable {

	private String taskId;

	private Long creationTime;

	private Long modificationTime;

	private Status status;

	private Priority priority;

	private UserBean asignee;

	private String definition;

	private UserBean creator;

	private UserBean lastModificator;

	//private Project project;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@JsonProperty
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name="creationTime")
	@JsonProperty
	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long currentDate) {
		this.creationTime = currentDate;
	}

	@Column(name="modificationTime")
	@JsonProperty
	public Long getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Long currentDate) {
		this.modificationTime = currentDate;
	}

	@Column(name="status")
	@JsonProperty
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
		setChanged();
		notifyObservers(Component.STATUS);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asignee_id", nullable = false)
	public UserBean getAsignee() {
		return asignee;
	}

	public void setAsignee(UserBean asignee) {
		this.asignee = asignee;
	}

	@Column(name="definition")
	@JsonProperty
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
		setChanged();
		notifyObservers(Component.DEFINITION);
	}

	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="creator_id")
	@JsonProperty
	public UserBean getCreator() {
		return creator;
	}

	public void setCreator(UserBean creator) {
		this.creator = creator;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="last_modificator_id")
	@JsonProperty
	public UserBean getLastModificator() {
		return lastModificator;
	}

	public void setLastModificator(UserBean lastModificator) {
		this.lastModificator = lastModificator;
	}

	@Column(name="priority")
	@Enumerated(EnumType.STRING)
	@JsonProperty
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
		setChanged();
		notifyObservers(Component.PRIORITY);
	}

}
