package com.fmi.javaee.vertex.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Email;

import com.fmi.javaee.vertex.project.ProjectEntity;
import com.fmi.javaee.vertex.task.TaskEntity;
import com.fmi.javaee.vertex.task.comment.CommentEntity;
import com.fmi.javaee.vertex.task.event.subscription.SubscriptionEntity;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = UserEntity.GET_ALL_USERS, query = UserEntity.GET_ALL_USERS_QUERY),
		@NamedQuery(name = UserEntity.GET_BY_USERNAME, query = UserEntity.GET_BY_USERNAME_QUERY),
		@NamedQuery(name = UserEntity.GET_BY_EMAIL, query = UserEntity.GET_BY_EMAIL_QUERY),
		@NamedQuery(name = UserEntity.GET_BY_EMAIL_PASS, query = UserEntity.GET_BY_EMAIL_PASS_QUERY) })

public class UserEntity {

	static final String GET_ALL_USERS = "getAllUsers";

	static final String GET_ALL_USERS_QUERY = "SELECT u FROM UserEntity u";

	static final String GET_BY_EMAIL = "getByEmail";

	static final String GET_BY_EMAIL_QUERY = "SELECT u FROM UserEntity u WHERE u.email = :email";

	static final String GET_BY_USERNAME = "getByUsername";

	static final String GET_BY_USERNAME_QUERY = "SELECT u FROM UserEntity u WHERE u.username = :username";

	static final String GET_BY_EMAIL_PASS = "getByEmailAndPass";

	static final String GET_BY_EMAIL_PASS_QUERY = "SELECT u FROM UserEntity u WHERE u.email = :email AND u.password = :password";

	@Id
	@GeneratedValue
	private long userId;

	@Column(name = "jobTitle")
	private String jobTitle;

	@Column(unique = true, name = "username")
	private String username;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String password;

	@Email
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "isGod")
	@ColumnDefault("false")
	private Boolean isGod;

	@OneToMany(mappedBy = "asignee")
	private Collection<TaskEntity> assignedTasks = new ArrayList<>();

	@ManyToMany(mappedBy = "members")
	private Collection<ProjectEntity> memberProjects = new ArrayList<>();

	@ManyToMany(mappedBy = "administrators")
	private Collection<ProjectEntity> adminProjects = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subscribedUser")
	private List<SubscriptionEntity> subscriptions;

	@OneToMany(mappedBy = "commentator")
	private List<CommentEntity> comments;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsGod() {
		return isGod;
	}

	public void setIsGod(Boolean isGod) {
		this.isGod = isGod;
	}

	public long getUserId() {
		return userId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public List<CommentEntity> getComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Collection<TaskEntity> getAssignedTasks() {
		return assignedTasks;
	}

	public void setAssignedTasks(Collection<TaskEntity> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	public Collection<ProjectEntity> getMemberProjects() {
		return memberProjects;
	}

	public void setMemberProjects(Collection<ProjectEntity> memberProjects) {
		this.memberProjects = memberProjects;
	}

	public Collection<ProjectEntity> getAdminProjects() {
		return adminProjects;
	}

	public void setAdminProjects(Collection<ProjectEntity> adminProjects) {
		this.adminProjects = adminProjects;
	}

	public List<SubscriptionEntity> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionEntity> subscriptions) {
		this.subscriptions = subscriptions;
	}
}
