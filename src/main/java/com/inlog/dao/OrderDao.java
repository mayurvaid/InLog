package com.inlog.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.inlog.entities.CountDataObject;
import com.inlog.entities.Order;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

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

	@Override
	public List<CountDataObject> getOrderDataForGraph() {
		List<DBObject> pipeLineList = new ArrayList<DBObject>();
		List<CountDataObject> countDataList = new ArrayList<CountDataObject>();
		DBObject group = new BasicDBObject("$group", new BasicDBObject("_id",
				new BasicDBObject("$month", "$createdAt")).append("dataCount",
				new BasicDBObject("$sum", 1)));
		DBObject project = new BasicDBObject("$project", new BasicDBObject(
				"dataCount", "$dataCount"));

		DBCollection orderCollection = mongoTemplate.getCollection("order");
		pipeLineList.add(group);
		pipeLineList.add(project);

		AggregationOutput output = orderCollection.aggregate(pipeLineList);

		for (DBObject result : output.results()) {
			CountDataObject obj = new CountDataObject();
			obj.setId(String.valueOf((Integer)result.get("_id")));
			obj.setDataCount((Integer)result.get("dataCount"));
			
			countDataList.add(obj);
		}

		return countDataList;
	}

}
