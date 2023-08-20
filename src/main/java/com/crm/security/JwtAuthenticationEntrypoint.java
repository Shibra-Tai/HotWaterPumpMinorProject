package com.crm.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntrypoint implements AuthenticationEntryPoint
{

	// This method is executed when an unauthorized(i.e a user not having a token) 
	//user tries to access the application
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
	AuthenticationException authException) throws IOException, ServletException 
	{
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!"); 	
	}

}
