package com.fmi.javaee.vertex.user;

public class User {

	private String userId;
	private String jobTitle;
	private String username;
	private String fullName;
	private Gender gender;
	private String email;
	private Boolean isGod;

	public User(UserEntity entity) {
		this.userId = entity.getUserId();
		this.jobTitle = entity.getJobTitle();
		this.username = entity.getUsername();
		this.fullName = entity.getFullName();
		this.gender = entity.getGender();
		this.email = entity.getEmail();
		this.isGod = entity.getIsGod();
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

}
