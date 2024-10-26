package com.example.koizaidemobackendserver.business.repo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.koizaidemobackendserver.business.model.Users;

@Repository
public interface Loginrepository extends MongoRepository<Users,Id>{

	Users findByUsername(String username);
	
	
	

}
