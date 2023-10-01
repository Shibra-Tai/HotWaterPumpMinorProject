package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Project;
import com.crm.repositories.ProjectRepository;

@Service
public class ProjectService 
{
	@Autowired
	private ProjectRepository projectRepository;
	
	public void save(Project project)
	{
		projectRepository.save(project);
	}
	

}
