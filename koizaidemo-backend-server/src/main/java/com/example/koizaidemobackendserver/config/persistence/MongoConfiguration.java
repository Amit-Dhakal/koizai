package com.example.koizaidemobackendserver.config.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {
	
	
	private MongoTemplate mongoTemplate;
	
	Logger log=LoggerFactory.getLogger(MongoConfiguration.class);
	
	
    @Autowired
	public MongoConfiguration(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;		
		if(this.mongoTemplate!=null) {
			
			log.info("MongoTemplate is Ready !!!!");
			
		}
		
	}
	
	
}



//constructor injection 
//field create 
//