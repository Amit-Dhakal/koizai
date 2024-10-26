package com.example.koizaidemobackendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
//@Profile(value={"local","dev","production","staging"})
public class KoizaidemoBackendServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoizaidemoBackendServerApplication.class, args);
	}
	
}