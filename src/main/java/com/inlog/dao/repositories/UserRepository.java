package com.inlog.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.inlog.entities.User;

/**
 * 
 * @author Mayur
 * 
 * UserRepository ovveriding the MongoRepository class to get access to somt of method
 * available internally
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{ 'username' : ?0 }")
	User getUserByUserName(String name);
}
