package com.crm.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crm.entities.Address;
import com.crm.entities.BasicDetailsRequest;
import com.crm.entities.Project;
import com.crm.entities.User;
import com.crm.repositories.AddressRepository;
import com.crm.repositories.ProjectRepository;
import com.crm.services.AddressService;
import com.crm.services.ProjectService;

@RestController
@RequestMapping("/")
public class BasicDetailsController 
{
	
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@PostMapping("/saveBasicDetails")
	// Saves the project detail and address details
	public ResponseEntity<?> saveBasicDetails(@RequestBody BasicDetailsRequest request)
	{
		BasicDetailsRequest basicDetailsRequest = null;
		Address address = null;
		try 
		{
			address = request.getAddress();
			
			addressService.save(address);
			
			
			basicDetailsRequest = new BasicDetailsRequest(address);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("DATABASE_ERROR",HttpStatus.OK);
		}
		
		
		return new ResponseEntity<BasicDetailsRequest>(basicDetailsRequest,HttpStatus.OK); 
		
	}
	
	
	// add for /saveBasicDetails/{id} , id should be address id
	
	
	
    // Will return project and address detail. If that project id doesn't exist, 
	//list will null is returned
    @GetMapping("/findBasicDetailsByProjectId/{projectId}")
    public Address findByProjectId(@PathVariable int projectId)
    {
    	
    	Project project = projectRepository.findById(projectId).get();
    	Address address = null;
    	if(project == null)
    	{
    		return null;
    	}
    	
    	try
    	{
    		address = addressService.findAddressByProjectId(projectId);
    	}
    	catch(NoSuchElementException nsee)
    	{
    		address = new Address();
    		return address;
    		//returning null object
    	}
    	catch(Exception e)
    	{
    		address = new Address();
    		return address;
    		//returning null object
    	}
    	
    	return address;
    	
    }
    
    // If null if returned, deletion operation was unsuccessfull
    @DeleteMapping("/deleteBasicDetailsByProjectId/{projectId}")
    public Project deleteByProjectId(@PathVariable int projectId)
    {
    	
    	Project project = projectRepository.findById(projectId).get();
    	if(project!=null)
    	{
    		Address address = addressService.findAddressByProjectId(projectId);
    		// As address table has FK-projectId and we have added cascaseAll constraint,
    		//deleting from address table will delete from project table too
    		addressRepository.delete(address);
    		
    		return project;
    	}
    	return null;
    }
	
    
	  @PutMapping("/editBasicDetail/{projectId}")
	  public Address UpdateBasicDetail(@PathVariable int projectId, @RequestBody BasicDetailsRequest request)
	  {
	  		Address oldAddress = addressService.findAddressByProjectId(projectId);
	  		Address newAddress = request.getAddress();
	  		oldAddress.setProject(newAddress.getProject());
	  		oldAddress.setBuildingName(newAddress.getBuildingName());
	  		oldAddress.setLotNumber(newAddress.getLotNumber());
	  		oldAddress.setPostcode(newAddress.getPostcode());
	  		oldAddress.setState(newAddress.getState());
	  		oldAddress.setStreetName(newAddress.getStreetName());
	  		oldAddress.setStreetNumber(newAddress.getStreetNumber());
	  		oldAddress.setStreetTypeSuffix(newAddress.getStreetTypeSuffix());
	  		oldAddress.setSuburb(newAddress.getSuburb());
	  		oldAddress.setUnitNumber(newAddress.getUnitNumber());
	  			
	  		addressRepository.save(oldAddress);
	  		return oldAddress;
	  		
	  }
    
    @GetMapping("/findAllBasicDetails")
	public List<Address> findAllBasicDetails()
	{
    	return addressRepository.findAll();
	}
    
    

	
}
