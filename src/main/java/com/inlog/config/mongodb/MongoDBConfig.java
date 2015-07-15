package com.inlog.config.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.inlog.commons.UserNameAuditor;
import com.inlog.dao.UserDao;
import com.mongodb.MongoClient;

/**
 * MONGODB CONFIG class
 * Create repositories from base package class location {@link UserDao} and from package com.inlog.dao.repositories
 * Looks for {@link UserNameAuditor} as reference bean for audting mongodb collections
 * @author Mayur
 *
 */
@Configuration
@EnableMongoRepositories("com.inlog.dao.repositories")
@EnableMongoAuditing(auditorAwareRef = "auditor")
@ComponentScan(basePackageClasses = {UserDao.class})
public class MongoDBConfig {
	@Value("${mongo-db-name}")
	private String dbName;

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), dbName);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;

	}

	public @Bean UserNameAuditor auditor() {
		return new UserNameAuditor();
	}
}
