package com.ness.knowledges.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ness.knowledges.domain.User;

@Service
public interface UserService extends UserDetailsService {
	public void save(User user);
	public User findUserByUsername(String username) throws UsernameNotFoundException;
	public void updateUserByUsername(String username, User newuser);
}
