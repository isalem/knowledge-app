package com.ness.knowledges.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.knowledges.persistent.UserRepository;
import com.ness.knowledges.persistent.model.UserEntity;

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
	
	public UserEntity findUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	public void saveUser(UserEntity user) {
		userRepository.save(user);
	}
}
