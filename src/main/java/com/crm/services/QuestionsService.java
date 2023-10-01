package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Questions;
import com.crm.repositories.QuestionsRepository;

@Service
public class QuestionsService {
	
	@Autowired
	QuestionsRepository questionsRepository;
	
	
	public void save(Questions question) {
		
		questionsRepository.save(question);
		
	}
}
