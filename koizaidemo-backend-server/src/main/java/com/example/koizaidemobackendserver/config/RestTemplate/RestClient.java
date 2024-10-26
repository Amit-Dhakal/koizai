package com.example.koizaidemobackendserver.config.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClient {

Logger logger=LoggerFactory.getLogger(RestClient.class);

@Bean	
public RestTemplate getTemplate() {
		
logger.info("Rest Template Initilaized "+new RestTemplate());
return new RestTemplate();
			
}

	
}
