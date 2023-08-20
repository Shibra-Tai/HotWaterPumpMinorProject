package com.crm.filter;

import java.io.IOException;

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
		System.out.println("Header: "+request.getHeader("Authorization"));
		System.out.println("TOKEN IS: "+requestToken);  //Token starts with "Bearer"
		
		
		if(request != null && requestToken.startsWith("Bearer"))
		{
			token = requestToken.substring(7); // remove "Bearer " from the request token
			try
			{
				userName = this.jwtTokenHelper.getUsernameFromToken(token);
				System.out.print("Token username: "+userName);
			}
			catch(IllegalArgumentException iae)
			{
				System.out.println("IllegalArgumentException occurred: "+iae.getMessage());
			}
			catch(ExpiredJwtException eje)
			{
				System.out.println("ExpiredJwtException occurred: "+eje.getMessage());
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
			System.out.println("Invalid token in the request");
		}
		
		
		// Now as we have retrieved token, we will validate it
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("Invalid token!!!");
			}
		}
		else
		{
			System.out.println("\nSomething went wrong");
		}
		
		filterChain.doFilter(request, response);
	}

}
