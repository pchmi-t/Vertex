package com.fmi.javaee.vertex.task;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmi.javaee.vertex.task.monitoring.Component;
import com.fmi.javaee.vertex.user.UserBean;

@Table(name="tasks")
@Entity
public class TaskBean extends Observable {

	//Persisted properties
	private String taskId;

	private Date creationTime;

	private Date modificationTime;

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
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date currentDate) {
		this.creationTime = currentDate;
	}

	@Column(name="modificationTime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty
	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date currentDate) {
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

	@JsonProperty
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="last_modificator_id")
	public UserBean getLastModificator() {
		return lastModificator;
	}

	public void setLastModificator(UserBean lastModificator) {
		this.lastModificator = lastModificator;
	}

	@JsonProperty
	@Column(name="priority")
	@Enumerated(EnumType.STRING)
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
		setChanged();
		notifyObservers(Component.PRIORITY);
	}

}
