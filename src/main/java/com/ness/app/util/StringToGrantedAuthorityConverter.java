package com.ness.app.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;

import com.ness.app.user.UserRole;

public class StringToGrantedAuthorityConverter implements Converter<String, GrantedAuthority> {

	@Override
	public UserRole convert(String source) {
		return UserRole.valueOf(source);
	}
}
