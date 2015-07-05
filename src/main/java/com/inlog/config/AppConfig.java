package com.inlog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.inlog.config.mongodb.MongoDBConfig;
import com.inlog.web.HelloController;

@Configuration
@Import({ MongoDBConfig.class })
@ComponentScan(basePackageClasses = { HelloController.class })
public class AppConfig {

}
