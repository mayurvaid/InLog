package com.inlog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.inlog.entities.Item;

@Repository
public class ItemDao extends BaseDao implements IItemDao {

	@Autowired
	public ItemDao(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	public List<Item> getItemByCriteria(Query query) {
		return mongoTemplate.find(query, Item.class);
	}

}
