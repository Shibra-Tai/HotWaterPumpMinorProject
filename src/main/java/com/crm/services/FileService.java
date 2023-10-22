package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.FileEntity;
import com.crm.repositories.FileRepository;

@Service
public class FileService 
{
	@Autowired 
	FileRepository fileRepository;
	
	public FileEntity getByProjectId(int projectId)
	{
		try
		{
			return fileRepository.findByprojectProjectId(projectId).get();
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION CAUGHT IN FileService Class");
			System.out.println("Probably: File with this projectid is not found");
			
		}
		return null;
	}

}
