package com.crm.security;



public class JwtAuthResponse 
{
	private String token;

	public JwtAuthResponse(String token) {
		
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + "]";
	}

	
	
}
