package com.ness.app.user;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ness.app.knowledge.KnowledgeWithSelection;
import com.ness.app.settings.PasswordSettingsForm;
import com.ness.app.settings.ProfileSettingsForm;

@Service
public interface UserService extends UserDetailsService {
	public User save(User user);
	public User saveAndLogin(User user);
	public User findUserById(Long id);
	public User findUserByUsername(String username) throws UsernameNotFoundException;
	public Set<User> findUsersWithKnowledges(Set<String> knowledges);
	public User updateUserProfile(ProfileSettingsForm profileSettingsForm);
	public User updateUserPassword(PasswordSettingsForm passwordSettingsForm);
	public List<KnowledgeWithSelection> findUsersKnowledgesWithSelection(String username);
}
