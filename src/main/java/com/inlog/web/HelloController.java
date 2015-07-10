package com.inlog.web;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import com.inlog.dao.repositories.UserRepository;

public class HelloController {
	@Autowired
	private MongoTemplate template;

	Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private UserRepository userRepo;

	// @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces =
	// "application/json")
	public String getGreeting(@PathVariable String name) {
		logger.info("--------------heere");
		String s = Jwts.builder().setSubject("Joe")
				.signWith(SignatureAlgorithm.HS256, "Mayur").compact();
		// s throw new AccessDeniedException("error");
		// Aggregation agg = newAggregation(
		// match(Criteria.where("username").is("tedbsajdgas")),
		// group("id").count().as("total"),
		// project("total").and("_id").previousOperation(),
		// sort(Sort.Direction.DESC, "total")
		//
		// );
		//
		// AggregationResults<User> resultGrp = template.aggregate(agg,
		// User.class, User.class);
		// System.out.println(resultGrp.getMappedResults().size());

		/*
		 * User user = new User(); user.setUsername(name);
		 * user.setPassword("test"); user.setId("2");
		 * 
		 * userRepo.save(user);
		 */

		// String result = "Hello " + name;
		// return userRepo.getUserByUserName("mayur1");

		return s;
	}
}