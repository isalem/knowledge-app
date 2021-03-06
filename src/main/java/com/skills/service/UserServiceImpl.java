package com.skills.service;


import com.google.common.collect.Lists;
import com.skills.domain.model.User;
import com.skills.domain.wrapper.SkillWithSelection;
import com.skills.persistent.UserRepository;
import com.skills.view.PasswordSettingsForm;
import com.skills.view.ProfileSettingsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userDetailService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = false)
    @CacheEvict(cacheNames = {"user", "skill"}, allEntries = true)
    public User save(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = false)
    @CacheEvict(cacheNames = {"user", "skill"}, allEntries = true)
    public User saveAndLogin(User user) {
        User savedUser = this.save(user);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(savedUser.getUsername(), savedUser.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        return savedUser;
    }

    @Override
    public List<User> findAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = false)
    @CacheEvict(cacheNames = "user", allEntries = true)
    public User updateUserProfile(ProfileSettingsForm profileSettingsForm) {
        User currentUser = userRepository.findOne(profileSettingsForm.getId());
        currentUser.setName(profileSettingsForm.getName());
        currentUser.setUsername(profileSettingsForm.getUsername());
        currentUser.setEmail(profileSettingsForm.getEmail());
        currentUser = userRepository.save(currentUser);

        updatePrincipalFor(currentUser);

        return currentUser;
    }

    @Override
    @Transactional(readOnly = false)
    @CacheEvict(cacheNames = "user", allEntries = true)
    public User updateUserPassword(PasswordSettingsForm passwordSettingsForm) {
        User currentUser = userRepository.findOne(passwordSettingsForm.getId());

        String rawPassword = passwordSettingsForm.getNewPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        currentUser.setPassword(encodedPassword);
        currentUser = userRepository.save(currentUser);

        updatePrincipalFor(currentUser);

        return currentUser;
    }

    @Override
    @Cacheable("user")
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "'ud for' + #username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) findUserByUsername(username);
    }

    @Override
    @Cacheable("user")
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Override
    public Set<User> findUsersWithSkills(Set<String> skills) {

        Set<User> result = new HashSet<>();
        for (String skill : skills) {

            List<User> users = userRepository.findAllUsersWithSkill(skill);

            result.addAll(users);

        }
        return result;
    }

    @Override
    @Cacheable(cacheNames = "user", key = "'skills for' + #username")
    public List<SkillWithSelection> findUsersSkillsWithSelection(String username) {
        User user = userRepository.findUserByUsername(username);
        return user.getSkills().stream()
                .map(k -> new SkillWithSelection(k, true))
                .collect(Collectors.toList());
    }

    private void updatePrincipalFor(final User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
