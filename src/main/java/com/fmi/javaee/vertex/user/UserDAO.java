package com.fmi.javaee.vertex.user;

import java.util.List;

public interface UserDAO {
	
	UserEntity getUser(long id);
	
	UserEntity getUser(String email, char[] password);
	
	List<UserEntity> getUsers(int limit, Integer offset);
	
	UserEntity createUser(UserEntity user);

	UserEntity getUserByEmail(String email);
	
	UserEntity getUserByUsername(String username);
}
