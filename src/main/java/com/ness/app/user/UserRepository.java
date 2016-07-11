package com.ness.app.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findUserByEmail(String email);
	User findUserByName(String name);
	User findUserByUsername(String username);
	
	@Query("from User u "
		 + "join u.knowledges k "
		 + "where k.title = ?1")
	List<Object[]> findAllUsersWithKnowledge(String knowledTitle);
}
