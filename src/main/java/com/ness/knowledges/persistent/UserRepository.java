package com.ness.knowledges.persistent;

import org.springframework.data.repository.CrudRepository;

import com.ness.knowledges.persistent.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findUserByEmail(String email);
	UserEntity findUserByFirstName(String firstName);
	UserEntity findUserByUsername(String username);
}
