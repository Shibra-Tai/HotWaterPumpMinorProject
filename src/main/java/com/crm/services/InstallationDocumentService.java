package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.crm.entities.InstallationDocument;
import com.crm.repositories.InstallationDocumentRepository;

@Service
public class InstallationDocumentService 
{
	@Autowired 
	InstallationDocumentRepository installationDocumentRepository;
	
	public InstallationDocument getByProjectId(int projectId)
	{
		try
		{
			return installationDocumentRepository.findByprojectProjectId(projectId).get();
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION CAUGHT IN InstallationDocumentService Class");
			System.out.println("Probably: Installation Document with this projectid is not found");
			
		}
		return null;
	}
}
