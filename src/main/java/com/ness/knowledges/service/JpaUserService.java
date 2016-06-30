package com.ness.knowledges.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ness.knowledges.domain.User;
import com.ness.knowledges.persistent.UserRepository;
import com.ness.knowledges.service.interfaces.UserService;

@Service(value = "userDetailService")
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUserByUsername(String username, User newUser) {
		User oldUser = findUserByUsername(username);
		
		oldUser.setEmail(newUser.getEmail());
		oldUser.setName(newUser.getName());
		oldUser.setUsername(newUser.getUsername());
		
		save(oldUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) findUserByUsername(username);
	}

	@Override
	public User findUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) throw new UsernameNotFoundException("User not found");
		return user;
	}
}
