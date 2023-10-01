package com.crm.entities;




public class RefreshTokenRequest 
{
	private String uuid;
	
	public RefreshTokenRequest()
	{}

	public RefreshTokenRequest(String uuid) {
		
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

}
