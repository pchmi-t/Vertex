package com.fmi.javaee.vertex.task.event;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "taskEvents")
@NamedQueries({
		@NamedQuery(query = "select e from EventEntity e join fetch e.subscribers sub where sub.email = :email", name = EventEntity.GET_BY_USER) })
public class EventEntity {

	static final String GET_BY_USER = "getEventsByUser";

	@Id
	@GeneratedValue
	@Column(name = "eventId")
	private long eventId;

	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Column(name = "before")
	private String before;

	@Column(name = "after")
	private String after;

	@Column(name = "eventType")
	@Enumerated(EnumType.STRING)
	private EventType type;

	@JoinColumn(name = "taskId")
	@OneToOne
	private TaskEntity refTask;

	@OneToOne
	@JoinColumn(name = "userId")
	private UserEntity refUser;

	@ManyToMany
	private List<UserEntity> subscribers;

	public long getEventId() {
		return eventId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public TaskEntity getRefTask() {
		return refTask;
	}

	public void setRefTask(TaskEntity refTask) {
		this.refTask = refTask;
	}

	public UserEntity getRefUser() {
		return refUser;
	}

	public void setRefUser(UserEntity refUser) {
		this.refUser = refUser;
	}

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public List<UserEntity> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<UserEntity> subscribers) {
		this.subscribers = subscribers;
	}

}
