package com.inlog.config;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.inlog.interceptors.WebInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(convertor());
		addDefaultHttpMessageConverters(converters);
	}

	@Bean
	public HttpMessageConverter<?> convertor(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new SimpleModule() {
			private static final long serialVersionUID = 1L;

			{
		        addSerializer(DateTime.class, new StdSerializer<DateTime>(DateTime.class) {
					private static final long serialVersionUID = 1L;

					@Override
		            public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		                 jgen.writeString(ISODateTimeFormat.dateHourMinuteSecondMillis().print(value));
		            }
		        });
		    }
		});
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		MappingJackson2HttpMessageConverter convertor = new MappingJackson2HttpMessageConverter();
		convertor.setObjectMapper(mapper);
		return convertor;
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webRequestInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
	public @Bean HandlerInterceptor webRequestInterceptor(){
		return new WebInterceptor();
	}
}
