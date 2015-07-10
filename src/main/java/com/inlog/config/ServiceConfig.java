package com.inlog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.inlog.services.UserService;

@Configuration
@ComponentScan(basePackageClasses = {UserService.class})
public class ServiceConfig {
}
