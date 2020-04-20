package com.tecnositaf.centrobackend.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources ({
	@PropertySource("classpath:application.properties"),
	
	@PropertySource(value = "classpath:/${env}/database.properties", ignoreResourceNotFound = false),
	@PropertySource(value = "classpath:/${env}/endpoint.properties", ignoreResourceNotFound = false),
	@PropertySource(value = "classpath:/${env}/filesystem.properties", ignoreResourceNotFound = false),
	@PropertySource(value = "classpath:/${env}/service.properties", ignoreResourceNotFound = false)

})
public class EnvironmentConfiguration {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${env}")
	private String env;
	
	@Value("${spring.data.mongodb.uri}")
	private String mongodatabaseUrl;
	
	@Value("${spring.datasource.url}")
	private String mysqldatabaseUrl;
	
	@PostConstruct
	void init(){
		log.info("env => " + env);
		log.info("mongo databaseUrl => " + mongodatabaseUrl);
		log.info("mysql databaseUrl => " + mysqldatabaseUrl);

	}
}
