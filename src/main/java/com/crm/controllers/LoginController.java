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
import com.crm.entities.RefreshToken;
import com.crm.entities.RefreshTokenRequest;
import com.crm.entities.User;
import com.crm.helper.JwtTokenHelper;
import com.crm.repositories.UserRepository;
import com.crm.services.RefreshTokenService;
import com.crm.services.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;


@RestController
@RequestMapping("/")
public class LoginController 
{	
	
	RefreshToken refreshToken = null;
	
	@Autowired
	UserService userService;
	
	UserDetails userDetails=null;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	 
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest)
	{
		String userNameFromToken = jwtAuthRequest.getUserName();
		String userPasswordFromToken = jwtAuthRequest.getUserPassword();
		
		
		if(userNameFromToken == null || userPasswordFromToken == null)
		{
			JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(null,null,null);
			
			return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);
		}
		
		
		try
		{
			boolean isValidUser = authenticate(userNameFromToken,userPasswordFromToken);
			if(!isValidUser)  // If not a valid user tries to login
			{
				JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(null,null,null);
				return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);
			}
			
			
			
			// below code will be executed if the user is authenticated
			
			refreshToken = refreshTokenService.createRefreshToken(jwtAuthRequest.getUserName());
			userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtAuthRequest.getUserName());
			
		}
		catch(UsernameNotFoundException unfe)
		{
			System.out.println("USER NOT FOUND IN DATABASE");
			JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(null,null,null);
			
			
			return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);
		}
		catch(Exception e)
		{
			System.out.println("--------------LOGIN CONTROLLER: JWT EXPIRED--------------");
		}
		String token = this.jwtTokenHelper.generateToken(userDetails);
		String userRole = userService.getUserRole(jwtAuthRequest.getUserName(), jwtAuthRequest.getUserPassword());
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(token,userRole,refreshToken.getUuid());
		
		System.out.println("TOKEN GENERATED");
		
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);
	}

	private boolean authenticate(String userName, String userPassword) 
	{
		String actualPassword = userService.findPasswordByUserName(userName);
		
		if(actualPassword!="" && actualPassword!=null && actualPassword.equals(userPassword))
		{
			return true;
		}
		
		
		return false;
	}
	
}
