package com.example.koizaidemobackendserver.business.service;

import java.util.List;
import java.util.Optional;

import com.example.koizaidemobackendserver.business.model.Users;


public interface IUserService {
	
public void saveUser(Users users);

public List<Users> getUsers();


public Users getUsersByUsername(String username);



}
