package com.fmi.javaee.vertex.task.event.subscription;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "subscriptions", uniqueConstraints = @UniqueConstraint(columnNames = { "userId", "taskId" }))
@NamedQueries({
		@NamedQuery(name = SubscriptionEntity.IS_SUBSCRIBED_QUERY, query = "SELECT s FROM SubscriptionEntity s WHERE s.subscribedUser = :user AND s.subscriptionTask = :task"),
		@NamedQuery(name = SubscriptionEntity.GET_SUBSCRIBED_QUERY, query = "SELECT s.subscribedUser FROM SubscriptionEntity s WHERE s.subscriptionTask = :task") })
public class SubscriptionEntity {

	static final String IS_SUBSCRIBED_QUERY = "isUserSubscribed";

	static final String GET_SUBSCRIBED_QUERY = "getSubscribedUsers";

	@Id
	@GeneratedValue
	private long subscriptionId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity subscribedUser;

	@ManyToOne
	@JoinColumn(name = "taskId")
	private TaskEntity subscriptionTask;

	public long getSubscriptionId() {
		return subscriptionId;
	}

	public UserEntity getSubscribedUser() {
		return subscribedUser;
	}

	public void setSubscribedUser(UserEntity subscribedUser) {
		this.subscribedUser = subscribedUser;
	}

	public TaskEntity getSubscriptionTask() {
		return subscriptionTask;
	}

	public void setSubscriptionTask(TaskEntity subscriptionTask) {
		this.subscriptionTask = subscriptionTask;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subscribedUser == null) ? 0 : subscribedUser.hashCode());
		result = prime * result + ((subscriptionTask == null) ? 0 : subscriptionTask.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionEntity other = (SubscriptionEntity) obj;
		if (subscribedUser == null) {
			if (other.subscribedUser != null)
				return false;
		} else if (!subscribedUser.equals(other.subscribedUser))
			return false;
		if (subscriptionTask == null) {
			if (other.subscriptionTask != null)
				return false;
		} else if (!subscriptionTask.equals(other.subscriptionTask))
			return false;
		return true;
	}

}
