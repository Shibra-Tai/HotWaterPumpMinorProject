package com.crm.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.Address;
import com.crm.entities.BasicDetailsRequest;
import com.crm.entities.Project;

@RestController
@RequestMapping("/")
public class BasicDetailsController 
{
	
	
	@PostMapping("/saveBasicDetails")
	public String saveBasicDetails(@RequestBody BasicDetailsRequest request)
	{
		Project project = request.getProject();
		Address address = request.getAddress();
		
		System.out.println("Project id : "+request.getProject().getProjectId());
		System.out.println("Address id : "+request.getAddress().getBuildingName());

		return "saved!!!";
	}
}
