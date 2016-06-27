package com.ness.knowledges.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ness.knowledges.persistent.UserRepository;
import com.ness.knowledges.persistent.model.CustomUserDetails;
import com.ness.knowledges.persistent.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findUserByUsername(username);
//		if (user != null) {
//			List<GrantedAuthority> authorities = new ArrayList<>();
//			authorities.add(user.getRole());
//		}
//	}
	
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
}
