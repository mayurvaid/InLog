package com.inlog.dao.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.inlog.entities.Email;

@Repository
public interface EmailRepository extends MongoRepository<Email, String>{

	@Query("{ 'toEmail' : ?0 }")
	List<Email> getEmailByToEmail(String toEmail);
}
