package com.crm.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.crm.entities.User;
import com.crm.repositories.UserRepository;
import com.crm.config.UserDetailsImpl;

@Component
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByuserName(username);
        return user.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }

}
