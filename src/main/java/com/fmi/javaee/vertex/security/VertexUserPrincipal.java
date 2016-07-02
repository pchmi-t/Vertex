package com.fmi.javaee.vertex.security;

import java.security.Principal;

public class VertexUserPrincipal implements Principal {

	private String name;

	public VertexUserPrincipal(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
