package com.crm.controllers.admin;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crm.entities.User;
import com.crm.repositories.UserRepository;
import com.crm.services.UserService;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController 
{

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    // Add new user
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Update existing user - PUT request for Updating
    @PutMapping("/edit/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User updatedUser) 
    {
        return userService.updateUser(userId, updatedUser);
    }

    // Delete by userId
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) 
    {
        userService.deleteUser(userId);
    }
    
    
    // Show all users
    // @GetMapping("/all")
    // public List<User> findAllUsers()
    // {
    // 	List allUsers = userRepository.findAll();
    // 	return allUsers;
    // }
    @PostMapping("/all")
    public List<User> findAllUsers(@RequestBody String userName)
    {
    	List<User> allUsers = userRepository.findAll();
    	User user = userService.findByUserName(userName).get();
    	boolean isCurrentUserRemoved = allUsers.remove(user);
    	
    	return allUsers;
    	
    }
    
    @GetMapping("/findByUserId/{userId}")
    public User findUserById(@PathVariable int userId)
    {
    	
    	return userRepository.findById(userId).get();
    	
    }
    
    @GetMapping("/findByUserEmail/{userEmail}")
    public User findUserByUserEmail(@PathVariable String userEmail)
    {
    	return userService.findByUserEmail(userEmail);
    }
    
    @GetMapping("/findByUserName/{userName}")
    public User findUserByUserName(@PathVariable String userName)
    {
    	return userService.findByUserName(userName).get();
    }
    
    
}
