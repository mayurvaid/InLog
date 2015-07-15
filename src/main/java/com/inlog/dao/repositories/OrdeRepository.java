package com.inlog.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inlog.entities.Order;

/**
 * 
 * @author Mayur
 * 
 * OrderRepository ovveriding the MongoRepository class to get access to somt of method
 * available internally
 *
 */
@Repository
public interface OrdeRepository extends MongoRepository<Order, String>{

}
