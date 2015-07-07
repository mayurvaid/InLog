package com.inlog.config.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.inlog.config.BaseConfig;
import com.inlog.config.UserNameAuditor;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("com.inlog.dao.repositories")
@PropertySource("classpath:properties/db-config.properties")
@EnableMongoAuditing(auditorAwareRef = "auditor")
public class MongoDBConfig extends BaseConfig {
	@Value("${mongo-db-name}")
	private String dbName;

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), dbName);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;

	}
	
	public @Bean UserNameAuditor auditor(){
		return new UserNameAuditor();
	}
}
