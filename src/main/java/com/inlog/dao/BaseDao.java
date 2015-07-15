package com.inlog.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@SuppressWarnings("unused")
public class BaseDao {
	protected final MongoTemplate mongoTemplate;
	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

	public BaseDao(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
