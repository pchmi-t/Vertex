package com.fmi.javaee.vertex.task.event;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "taskEvents")
@NamedQueries({
		@NamedQuery(query = "select e from EventEntity e join fetch e.refUser where e.refUser.email = :email", name = EventEntity.GET_BY_USER) })
public class EventEntity {

	static final String GET_BY_USER = "getEventsByUser";

	private String eventId;

	private Date timestamp;

	private String before;
	
	private String after;
	
	private EventType type;

	private TaskEntity refTask;

	private UserEntity refUser;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "eventId", unique = true, nullable = false)
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@JoinColumn(name = "task_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public TaskEntity getRefTask() {
		return refTask;
	}

	public void setRefTask(TaskEntity refTask) {
		this.refTask = refTask;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	public UserEntity getRefUser() {
		return refUser;
	}

	public void setRefUser(UserEntity refUser) {
		this.refUser = refUser;
	}

	@Column(name = "before")
	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	@Column(name = "after")
	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	@Column(name = "eventType")
	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	
	

}
