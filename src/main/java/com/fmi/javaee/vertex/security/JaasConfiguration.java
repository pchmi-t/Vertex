package com.fmi.javaee.vertex.security;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

public class JaasConfiguration extends Configuration {

	private static final String MODULE_CLASS = VertexLoginModule.class.getCanonicalName();

	private static final String DEBUG_OPTION = "debug";

	@Override
	public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
		Map<String, Object> options = new HashMap<String, Object>(); 
        options.put(DEBUG_OPTION, true); 
        
        AppConfigurationEntry vertexAppConfig = new AppConfigurationEntry(MODULE_CLASS, AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT, options); 
        return new AppConfigurationEntry[] { vertexAppConfig }; 
	}

}
