package com.inlog.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.inlog.entities.CountDataObject;
import com.inlog.entities.User;

@Repository
public class UserDao extends BaseDao implements IUserDao {

	@Autowired
	public UserDao(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	public Integer getUserCountByUserName(String username) {
		Aggregation agg = newAggregation(
				match(Criteria.where("username").is(username)),
				group("id").count().as("dataCount"), project("dataCount")
						.and("_id").previousOperation());

		AggregationResults<CountDataObject> result = mongoTemplate.aggregate(
				agg, User.class, CountDataObject.class);
		List<CountDataObject> dataCount = result.getMappedResults();

		if (dataCount != null && dataCount.size() > 0) {
			CountDataObject countDataObject = dataCount.get(0);
			return countDataObject.getDataCount();
		}
		
		return 0;
	}
}
