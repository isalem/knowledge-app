package com.skills.persistent;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skills.domain.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findUserByEmail(String email);
	User findUserByName(String name);
	User findUserByUsername(String username);
	
	@Query("select u "
		 + "from User u "
		 + "join u.skills k "
		 + "where k.title = ?1")
	List<User> findAllUsersWithSkill(String skillTitle);
}
