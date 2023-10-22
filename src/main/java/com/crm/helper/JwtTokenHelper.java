package com.crm.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.crm.config.UserDetailsImpl;
import com.crm.config.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//All the operations related to token will be done here
@Component
public class JwtTokenHelper 
{
	public static final long TOKEN_VALIDITY = 100*60*100; //1minute 	//specify after how many milisec, token expires
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	//SECRET is for digital signature of token
	
	
	//Will get userName from token
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
		//setSubject() : used for setting the username in jwt	
	}
	
	//Will get Expiration Date from token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);	
		
	}
	
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
		
	}
	
	
	// To get data from token, first SECRET will be required to specify to decode the token
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}
	
	
	private Boolean isTokenExpired(String token)
	{
		final Date expirationDate = getExpirationDateFromToken(token);
		System.out.println("EXP DATE--------->"+expirationDate);
		return expirationDate.before(new Date());
		
	}
	
	
	// Generates token for user:
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername()); 
		
	}
	
	
	// This will set all claims of token
	private String doGenerateToken(Map<String, Object> claims, String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				
				
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
		
	}
	
	
	// Is token valid?
	public boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = getUsernameFromToken(token);
		
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}
	
	
	// Not used in this application as Logout Controller is removed
	public String invalidateToken(String token)
	{
		String invalidToken = null;
		String userName = getUsernameFromToken(token);
		UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(userName);;
		

		Claims claims = getAllClaimsFromToken(token);
		
	
		System.out.println("Invalidating date: "+claims.getExpiration());
		return   Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + 0))
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, SECRET)
				.compact();
		

	}
}
