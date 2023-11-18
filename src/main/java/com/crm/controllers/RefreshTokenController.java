package com.crm.controllers;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.config.UserDetailsServiceImpl;
import com.crm.entities.RefreshToken;
import com.crm.entities.RefreshTokenRequest;
import com.crm.helper.JwtTokenHelper;
import com.crm.security.JwtAuthResponse;
import com.crm.services.RefreshTokenService;
import com.crm.services.UserService;

import io.jsonwebtoken.security.InvalidKeyException;


@RestController
@RequestMapping("/")
public class RefreshTokenController 
{
RefreshToken refreshToken = null;
	
	@Autowired
	UserService userService;
	
	UserDetails userDetails=null;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	 
	
	//This api is bypassed, like login (means JWT authetication filter is not applied to this)
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	// This api is bypassed, like login (means JWT authetication filter is not applied to this)
		@PostMapping("/refreshToken")
		public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws InvalidKeyException, java.security.InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException
		{
			
			RefreshToken refreshToken = refreshTokenService.findByUuid(refreshTokenRequest.getUuid()).get();
			
			RefreshToken refreshTokenExpiryValidation = refreshTokenService.verifyExpiry(refreshToken);
			 
			if(refreshTokenExpiryValidation != null)
			{
				int userId = refreshTokenExpiryValidation.getUser().getUserId();
				
				String userName = userService.findUserNameById(userId);
				
				
				userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
				
				
				String token = this.jwtTokenHelper.generateToken(userDetails);
				String userRole = userService.findUserRoleById(userId);
				JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(token,userRole,refreshToken.getUuid());
				
				System.out.println("TOKEN REFRESHED");
				
				return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);
			}
					
			else if(refreshTokenExpiryValidation == null)
			{
				System.out.println("TOKEN EXPIRED");
			}
			return new ResponseEntity<String>("IMPROPER CREDENTIALS PROVIDED, TRY AGAIN",HttpStatus.OK);
			
		}
		
}
