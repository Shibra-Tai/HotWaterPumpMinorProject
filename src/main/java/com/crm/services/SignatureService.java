package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Signature;
import com.crm.repositories.SignatureRepository;

@Service
public class SignatureService 
{
	@Autowired 
	SignatureRepository signatureRepository;
	
	//This method returns null if UploadQuestions with demanded projectId is absent
		public Signature getByProjectId(int projectId)
		{
			
			try
			{
				return signatureRepository.findByprojectProjectId(projectId).get();
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION CAUGHT IN SignatureService Class");
				System.out.println("Probably: SignatureService with this projectid is not found");
				
			}
			return null;
		}

}
