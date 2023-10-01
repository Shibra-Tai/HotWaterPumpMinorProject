package com.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Address;
import com.crm.repositories.AddressRepository;

@Service
public class AddressService 
{
	@Autowired
	private AddressRepository addressRepository;
	
	public void save(Address address)
	{
		System.out.println(addressRepository.save(address));
		
	}
	
	// If corresponding Address is not found, null is returned
	public Address findAddressByProjectId(int projectId)
	{
		
		List<Address> list = addressRepository.findAll();
		
		for(Address a : list)
		{
			int pid = a.getProject().getProjectId();
			if(pid == projectId)
			{
				return a;
			}
		}
		
		return null;
	}

}
