package com.ness.knowledges.security;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
	ROLE_EMPLOY("Employ"),
	ROLE_HR("HR"),
	ROLE_BUSINESS("Business");

	private String description;
	
	private UserRole(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String getAuthority() {
		return name();
	}

}
