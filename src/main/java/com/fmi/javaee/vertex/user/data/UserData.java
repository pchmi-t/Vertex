package com.fmi.javaee.vertex.user.data;

import com.fmi.javaee.vertex.user.Gender;
import com.fmi.javaee.vertex.user.UserBean;

public interface UserData {
	UserBean getUser(Long id);
	
	UserBean getUser(String email, String password);
	
	UserBean getUser(UserCriterion criterion);
	
	UserCriterion getUserCriterion();
	
	UserBean getUsers(int limit, Integer offset);
	
	UserBean getUsersByJobTitle(String jobTitle);
	
	UserBean getUsersByGender(Gender gender);
	
	UserBean createUser(UserBean user);
}
