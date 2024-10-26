package com.example.koizaidemobackendserver.business.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Component
@Document(collection="LoginUsers")
public class Users {
	private String id;
	private String username;
	private String password;
	

}
