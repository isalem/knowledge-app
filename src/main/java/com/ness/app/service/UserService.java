package com.ness.app.service;


import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ness.app.domain.model.User;
import com.ness.app.domain.wrapper.SkillWithSelection;
import com.ness.app.view.PasswordSettingsForm;
import com.ness.app.view.ProfileSettingsForm;

@Service
public interface UserService extends UserDetailsService {
	public User save(User user);
	public User saveAndLogin(User user);
	public List<User> findAllUsers();
	public Page<User> findAllUsers(Pageable page);
	public User findUserById(Long id);
	public User findUserByUsername(String username) throws UsernameNotFoundException;
	public Set<User> findUsersWithSkills(Set<String> skills);
	public User updateUserProfile(ProfileSettingsForm profileSettingsForm);
	public User updateUserPassword(PasswordSettingsForm passwordSettingsForm);
	public List<SkillWithSelection> findUsersSkillsWithSelection(String username);
}
