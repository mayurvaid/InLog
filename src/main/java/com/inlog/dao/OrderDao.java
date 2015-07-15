package com.inlog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.inlog.entities.Order;

@Repository
public class OrderDao extends BaseDao implements IOrderDao {

	@Autowired
	public OrderDao(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	public List<Order> getOrderByCriteria(Query query) {
		return mongoTemplate.find(query, Order.class);
	}

}
