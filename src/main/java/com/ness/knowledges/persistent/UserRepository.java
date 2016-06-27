package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.persistent.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findUserByEmail(String email);
	User findUserByFirstName(String firstName);
	User findUserByUsername(String username);
}
