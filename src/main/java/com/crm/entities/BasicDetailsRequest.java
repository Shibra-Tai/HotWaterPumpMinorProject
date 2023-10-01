package com.crm.entities;

public class BasicDetailsRequest 
{
	
	private Address address;
	
	public BasicDetailsRequest() {

	}
	
	public BasicDetailsRequest(Address address) {
		
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	

}
