package com.inlog.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.inlog.entities.User;

@Component
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{ 'username' : ?0 }")
	User getUserByUserName(String name);
}
