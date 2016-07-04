package com.fmi.javaee.vertex.user.data;

import java.util.List;

import com.fmi.javaee.vertex.user.Gender;
import com.fmi.javaee.vertex.user.UserEntity;

public interface UserData {
	UserEntity getUser(String id);
	
	UserEntity getUser(String email, char[] password);
	
	List<UserEntity> getUsers(int limit, Integer offset);
	
	UserEntity getUsersByJobTitle(String jobTitle);
	
	UserEntity getUsersByGender(Gender gender);
	
	UserEntity createUser(UserEntity user);

	UserEntity getUserByEmail(String email);
	
	UserEntity getUserByUsername(String username);
}
