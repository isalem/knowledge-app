package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findUserByEmail(String email);
	User findUserByName(String name);
	User findUserByUsername(String username);
}
