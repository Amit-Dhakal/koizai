package com.example.koizaidemobackendserver.config.Asynchronous;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@EnableAsync
public class AsyncConfiguration {
	
	@Bean("koizaiAsynch")
	public Executor taskExecutor() {
		
		ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
			
		executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("koizaiAsynch-");
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(120);
        executor.initialize();		
		return executor;
		
	}
		
}


//multithreading
//one task is not dependent to others 
//work independently