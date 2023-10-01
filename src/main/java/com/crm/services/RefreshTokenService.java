package com.crm.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.RefreshToken;
import com.crm.entities.User;
import com.crm.repositories.RefreshTokenRepository;
import com.crm.repositories.UserRepository;
import com.google.common.base.Optional;


// NOTE: AS NEW TOKEN IS REFRESHED BEFORE IT HAS EXPIRED, THE OLD TOKEN WILL STILL
// BE VALID...THIS ISSUE IS STILL TO BE SOLVED
@Service
public class RefreshTokenService 
{
	@Autowired
	RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public RefreshToken createRefreshToken(String userName)
	{
		
		
		RefreshToken refreshToken = new RefreshToken(
				userRepository.findByUserName(userName).get().getUserId(),
				UUID.randomUUID().toString(),
				new Date(System.currentTimeMillis() + 600000),  
				userRepository.findByUserName(userName).get()
				);
		
		List<RefreshToken> list = refreshTokenRepository.findAll();
		for(RefreshToken rf: list)
		{
			int userId = rf.getUser().getUserId();
			if(userId == userRepository.findByUserName(userName).get().getUserId())
			{
				refreshTokenRepository.delete(rf);
			}
		}
		refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}
	
	
	public Optional<RefreshToken> findByUuid(String uuid)
	{
		return refreshTokenRepository.findByUuid(uuid); 
	}
	

	// This method checks if the token has expired, 
//	if yes it will delete that token and return null. 
//	Else if the token is not expired, it will return the RefreshToken itself
	public RefreshToken verifyExpiry(RefreshToken refreshToken)
	{
		if(refreshToken.getExpiryDate().before(new Date()))
		{
			refreshTokenRepository.delete(refreshToken);
			System.out.println("is expired..in method");
			return null;
		}
		
		return refreshToken;
	}
	
	public void deleteByUser(User user)
	{
		RefreshToken rf = refreshTokenRepository.findByUser(user).get();
		refreshTokenRepository.delete(rf);
		
	}
	
}
