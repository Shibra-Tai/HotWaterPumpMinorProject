package com.crm.security;



public class JwtAuthResponse 
{
	private String token;
	private String userRole;
	private String uuid;
	
	public JwtAuthResponse()
	{
		
	}

	

	public JwtAuthResponse(String token, String userRole, String uuid) 
	{
		
		this.token = token;
		this.userRole = userRole;
		this.uuid = uuid;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
	
	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + "]";
	}

	
	
}
