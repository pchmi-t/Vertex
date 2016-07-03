package com.fmi.javaee.vertex.user.data;

import java.util.List;

import com.fmi.javaee.vertex.user.Gender;
import com.fmi.javaee.vertex.user.UserBean;

public interface UserData {
	UserBean getUser(String id);
	
	UserBean getUser(String email, char[] password);
	
	List<UserBean> getUsers(int limit, Integer offset);
	
	UserBean getUsersByJobTitle(String jobTitle);
	
	UserBean getUsersByGender(Gender gender);
	
	UserBean createUser(UserBean user);

	UserBean getUserByEmail(String email);
}
