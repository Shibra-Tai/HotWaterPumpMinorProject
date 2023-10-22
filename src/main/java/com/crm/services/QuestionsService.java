package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Questions;
import com.crm.entities.UploadQuestions;
import com.crm.repositories.QuestionsRepository;
import com.crm.repositories.UploadQuestionsRepository;

@Service
public class QuestionsService {
	
	@Autowired
	UploadQuestionsRepository repo;
	
	
	public boolean saveQuestions(UploadQuestions que) {
		
		try {
			repo.save(que);
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	public UploadQuestions getQuestions(int projectId) {
		UploadQuestions ans=null;
		try {
			 ans=repo.findByprojectProjectId(projectId).get();
		}catch (Exception e) {
			System.out.println(e);
			return ans;
		}
		return ans;
	}
}