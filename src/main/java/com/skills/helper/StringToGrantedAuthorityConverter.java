package com.skills.helper;

import com.skills.domain.model.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;

public class StringToGrantedAuthorityConverter implements Converter<String, GrantedAuthority> {

    @Override
    public UserRole convert(String source) {
        return UserRole.valueOf(source);
    }
}
