package com.crm.services;
import com.crm.config.UserDetailsImpl;
import com.crm.entities.User;
import com.crm.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
	private List<User> listOfUser = new ArrayList<>();
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}
	
	public Optional<User> findByUserName(String userName)
	{
		return userRepository.findByUserName(userName);
	}
	
	public String getUserRole(String name,String password) 
	{
		User user=userRepository.findByUserName(name).get();
		
		return user.getUserRole();
	}
	
	public int findIdByUserName(String userName)
	{
		User user = userRepository.findByUserName(userName).get();
		return user.getUserId();
	}
	
	public String findUserNameById(int userId)
	{
		User user = userRepository.findById(userId).get();
		return user.getUserName();
	}
	
	public String findPasswordByUserName(String userName)
	{
		User user = userRepository.findByUserName(userName).get();
		return user.getUserPassword();
	}
	
	public String findUserRoleById(int userId)
	{
		User user = userRepository.findById(userId).get();
		return user.getUserRole();
	}
	
	
	
	public User createUser(User newUser) 
	{
		userRepository.save(newUser);
		return newUser;
    }

    // Update an existing user
    public User updateUser(int userId, User updatedUser) 
    {
        User user = userRepository.findById(userId).get();
        
        user.setUserEmail(updatedUser.getUserEmail());
        user.setUserName(updatedUser.getUserName());
        user.setUserRole(updatedUser.getUserRole());
        user.setUserPassword(updatedUser.getUserPassword());
        
        userRepository.save(user);
        return user;
       
    }

    // Delete a user by ID
    public void deleteUser(int userId) 
    {
    	User user = userRepository.findById(userId).get();
    	userRepository.delete(user);
    	
    }

	public User findByUserEmail(String userEmail)
	{
		List<User> listOfAllUsers = userRepository.findAll();
		for(User u: listOfAllUsers)
		{
			if(u.getUserEmail().equals(userEmail))
			{
				return u;
			}
		}
		
		return null;
	}
	
	
	public String findUserEmailByUserId(int userId)
	{
		
		return userRepository.findById(userId).get().getUserEmail();
	}
	
	public List<User> findAllInstallers()
	{
		List<User> listOfAllUsers = userRepository.findAll();
		List<User> listOfAllInstallers = new ArrayList<>();
		for(User u: listOfAllUsers)
		{
			if(u.getUserRole().toUpperCase().equals("INSTALLER"))
			{
				listOfAllInstallers.add(u);
			}
		}
		
		return listOfAllInstallers;
	}
}
