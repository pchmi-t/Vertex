package com.fmi.javaee.vertex.task.event.subscription;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.user.UserEntity;

@Entity
@Table(name = "subscriptions", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "task_id" }))
@NamedQueries({
		@NamedQuery(name = SubscriptionEntity.IS_SUBSCRIBED_QUERY, query = "SELECT s FROM SubscriptionEntity s WHERE s.subscribedUser = :user AND s.subscriptionTask = :task"),
		@NamedQuery(name = SubscriptionEntity.GET_SUBSCRIBED_QUERY, query = "SELECT s.subscribedUser FROM SubscriptionEntity s WHERE s.subscriptionTask = :task")

})
public class SubscriptionEntity {

	static final String IS_SUBSCRIBED_QUERY = "isUserSubscribed";

	static final String GET_SUBSCRIBED_QUERY = "getSubscribedUsers";

	private String subscriptionId;

	private UserEntity subscribedUser;

	private TaskEntity subscriptionTask;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	public UserEntity getSubscribedUser() {
		return subscribedUser;
	}

	public void setSubscribedUser(UserEntity subscribedUser) {
		this.subscribedUser = subscribedUser;
	}

	@JoinColumn(name = "task_id")
	@ManyToOne(fetch = FetchType.LAZY)
	public TaskEntity getSubscriptionTask() {
		return subscriptionTask;
	}

	public void setSubscriptionTask(TaskEntity subscriptionTask) {
		this.subscriptionTask = subscriptionTask;
	}

}
