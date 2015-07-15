package com.inlog.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.inlog.entities.Order;

public interface IOrderDao {
	List<Order> getOrderByCriteria(Query query);
}
