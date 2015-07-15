package com.inlog.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.inlog.entities.Item;

public interface IItemDao {
	List<Item> getItemByCriteria(Query query);
}
