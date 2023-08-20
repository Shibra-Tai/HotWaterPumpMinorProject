package com.crm.services;
import com.crm.entities.User;
import com.crm.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	private List<User> listOfUser = new ArrayList<>();
	
	@Autowired
	private UserRepository userRepository;
	
	public UserService() 
	{
		listOfUser.add(new User(101, "Shibra","abc@gmail.com","shibra123","admin"));
		listOfUser.add(new User(102, "Pal","pqr@gmail.com","pal123","admin"));
		listOfUser.add(new User(101, "Shibra","xyz@gmail.com","pranay123","admin"));
	}
	
	public List<User> getUsers()
	{
		return listOfUser;
	}
	
}
