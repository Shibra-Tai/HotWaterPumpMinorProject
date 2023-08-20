package com.crm.controllers;

import com.crm.security.JwtAuthResponse;
import com.crm.security.JwtAuthRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.crm.config.UserDetailsServiceImpl;
import com.crm.entities.User;
import com.crm.helper.JwtTokenHelper;
import com.crm.services.UserService;


@RestController
@RequestMapping("/")
public class LoginController 
{	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	 
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest)
	{
		System.out.println("In method");
		System.out.println("in createToken(): username: "+jwtAuthRequest.getUserName());
		//this.authenticate(jwtAuthRequest.getUserName(), jwtAuthRequest.getUserPassword());
		//System.out.println("Authenticated");
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtAuthRequest.getUserName());
		System.out.println(userDetails.getUsername());
		//System.out.println("error in user details")
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(token);
		
		jwtAuthResponse.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);
	}

	private void authenticate(String userName, String userPassword) 
	{
		
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(userName, userPassword);
		try
		{
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}
		catch(DisabledException de)
		{
			System.out.println("Not able to create token.....Exception occurred");
			System.out.println("DisabledException occurred: "+de.getMessage());
		}
		
		
	}
	
}
