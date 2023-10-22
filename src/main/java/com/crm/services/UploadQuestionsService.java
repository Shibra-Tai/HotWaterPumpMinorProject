package com.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.UploadQuestions;
import com.crm.repositories.UploadQuestionsRepository;

@Service
public class UploadQuestionsService 
{
	@Autowired
	UploadQuestionsRepository uploadQuestionsRepository;
	
	
	//This method returns null if UploadQuestions with demanded projectId is absent
	public UploadQuestions getByProjectId(int projectId)
	{
		
		try
		{
			return uploadQuestionsRepository.findByprojectProjectId(projectId).get();
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION CAUGHT IN UploadQuestionsService Class");
			System.out.println("Probably: Upload Question with this projectid is not found");
			
		}
		return null;
	}
}
