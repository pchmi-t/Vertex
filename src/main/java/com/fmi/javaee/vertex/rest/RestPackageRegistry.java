package com.fmi.javaee.vertex.rest;

import java.util.ArrayList;
import java.util.List;

public class RestPackageRegistry {
	
	private static final List<String> REST_PACKAGES = new ArrayList<>();
	
	public static void registerPackage(Package restPackage) {
		REST_PACKAGES.add(restPackage.getName());
	}
	
	public static String[] getRestPackages() {
		return REST_PACKAGES.toArray(new String[REST_PACKAGES.size()]);
	}

}
