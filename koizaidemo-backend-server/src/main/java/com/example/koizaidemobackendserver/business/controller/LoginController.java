package com.example.koizaidemobackendserver.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.koizaidemobackendserver.business.model.Users;
import com.example.koizaidemobackendserver.business.repo.Loginrepository;
import com.example.koizaidemobackendserver.business.service.IUserService;


@RestController
@RequestMapping("/api/v1/")
public class LoginController {

	@Autowired
	IUserService iuserService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUsers(@RequestBody Users users) throws InterruptedException{	
		
		iuserService.saveUser(users);
		return new ResponseEntity<>(iuserService.getUsers(),HttpStatus.ACCEPTED);		
	}
	
	
	
	@GetMapping("/getall")
	public ResponseEntity<?> getUsers(){	
	return new ResponseEntity<>(iuserService.getUsers(),HttpStatus.ACCEPTED);		
	}
	
	
	@GetMapping("/get/{username}")
	public ResponseEntity<?> getUsersByName(@PathVariable String username){	
		
		System.out.println(username);
	return new ResponseEntity<>(iuserService.getUsersByUsername(username),HttpStatus.ACCEPTED);		
	}
	
	
}
