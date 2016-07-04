package com.fmi.javaee.vertex.project;

import java.util.Set;

public class ProjectRequest {

	private String projectName;
	private String projectDescription;
	private Set<String> adminEmails;
	private Set<String> memberEmails;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Set<String> getAdminEmails() {
		return adminEmails;
	}

	public void setAdminEmails(Set<String> adminEmails) {
		this.adminEmails = adminEmails;
	}

	public Set<String> getMemberEmails() {
		return memberEmails;
	}

	public void setMemberEmails(Set<String> memberEmails) {
		this.memberEmails = memberEmails;
	}

}
