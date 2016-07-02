package com.fmi.javaee.vertex.factory;

import com.fmi.javaee.vertex.user.data.UserData;
import com.fmi.javaee.vertex.user.data.impl.UserDataImpl;

public class Factory {

	private Factory(){
	}
	
	public static Factory getInstance() {
		return new Factory();
	}
	
	public UserData getUserData() {
		return new UserDataImpl();
	}
	
}
