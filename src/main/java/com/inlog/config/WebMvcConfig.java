package com.inlog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
		converters.add(convertor());
		addDefaultHttpMessageConverters(converters);
	}
	
	@Bean
	public HttpMessageConverter<?> convertor(){
		return new MappingJackson2HttpMessageConverter();
	}
	
}
