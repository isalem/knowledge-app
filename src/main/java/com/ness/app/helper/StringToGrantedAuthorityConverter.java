package com.ness.app.helper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;

import com.ness.app.domain.model.UserRole;

public class StringToGrantedAuthorityConverter implements Converter<String, GrantedAuthority> {

	@Override
	public UserRole convert(String source) {
		return UserRole.valueOf(source);
	}
}
