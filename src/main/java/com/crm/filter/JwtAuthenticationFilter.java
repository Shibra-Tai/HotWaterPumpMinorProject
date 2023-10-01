package com.crm.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crm.config.UserDetailsServiceImpl;
import com.crm.helper.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



// This should be called when an end-point is requested - this will be executed before endpoint is hit
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	String userName=null;
	String token=null;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	throws ServletException, IOException
	{
		String requestToken = request.getHeader("Authorization");
		System.out.println("In Filter -> Authorization: "+requestToken);
	
		if(requestToken == null) // Token has not been generated yet
		{
			
			System.out.println("Request does not contain token");
			
		}
		
		else if(request != null && requestToken.startsWith("Bearer") && requestToken != null)
		{
			token = requestToken.substring(7); // remove "Bearer " from the request token
			System.out.println("2. Token: "+token);
			try
			{
				userName = this.jwtTokenHelper.getUsernameFromToken(token);
				System.out.println("Token username-> "+userName);
				
			}
			catch(IllegalArgumentException iae)
			{
				System.out.println("IllegalArgumentException occurred: "+iae.getMessage());
				
			}
			catch(ExpiredJwtException eje)
			{
				// enters here as soon as an expired token is read
				System.out.println("-----------------In Filter");
				System.out.println("Expired Jwt Exception occurred: "+eje.getMessage());
				
			}
			catch(MalformedJwtException mje)
			{
				System.out.println("MalformedJwtException occurred: "+mje.getMessage());
				
			}
			catch(Exception e)
			{
				System.out.println("Exception occurred: "+e.getMessage());
				
			}
			
		}
		else
		{
			System.out.println("USER LOGGED IN FOR FIRST TIME");
			// This will happen if token is invalid/null
		}
		
		
		// Now as we have retrieved token, we will validate it
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			try 
			{
				UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
				
				if(jwtTokenHelper.validateToken(token, userDetails))
				{  
					System.out.println("In controller printing exp::::"+jwtTokenHelper.getExpirationDateFromToken(token));
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				else
				{
					System.out.println("INVALID_TOKEN");
					// When the token is incorrect, this response will be returned
				}
			}
			catch(Exception e)
			{
				System.out.println("__EXCEPTION: ____"+e.getMessage());
			}
		}
		
		System.out.println("Reached here");
		filterChain.doFilter(request, response);
	}

}
