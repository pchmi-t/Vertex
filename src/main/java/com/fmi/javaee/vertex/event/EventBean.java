package com.fmi.javaee.vertex.event;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.user.UserBean;

@Table(name="event")
@Entity
public class EventBean {
	
	private String eventId;
	
	private Date creationTime;
	
	private String description;
	
	private TaskBean refTask;

	private UserBean refUser;
	
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@JsonProperty
	@Column(name="eventId", unique = true, nullable = false)
	public String getEventId() {
		return eventId;
	}
	
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Column(name="creationTime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name="description")
	@JsonProperty
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="task_id")
	@JsonProperty
	public TaskBean getRefTask() {
		return refTask;
	}
	
	public void setRefTask(TaskBean refTask) {
		this.refTask = refTask;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	@JsonProperty
	public UserBean getRefUser() {
		return refUser;
	}

	public void setRefUser(UserBean refUser) {
		this.refUser = refUser;
	}
	
	
}
