package com.example.koizaidemobackendserver.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.koizaidemobackendserver.business.model.Users;
import com.example.koizaidemobackendserver.business.repo.Loginrepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserserviceImpl implements IUserService{

	@Autowired
	Loginrepository loginrepository;
	
	@Override
	public void saveUser(Users users) {
		// TODO Auto-generated method stub			
		System.out.println("Saved Users");
		loginrepository.save(users);
	}

	
	
	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return loginrepository.findAll();
		
		
	}



	@Override
	public Users getUsersByUsername(String username) {
		// TODO Auto-generated method stub
		return loginrepository.findByUsername(username);
	}

	
	
}




//save into mongodb username and password data
//retrieve data
