package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Address;
import com.crm.repositories.AddressRepository;

@Service
public class AddressService 
{
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address)
	{
		return addressRepository.save(address);
	}
	


}
