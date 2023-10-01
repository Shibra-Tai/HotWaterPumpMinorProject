package com.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.Questions;

import com.crm.services.QuestionsService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class QuestionsController {
	
	@Autowired
	QuestionsService questionsService;
	
	@PostMapping("/save-question")
	public String saveQuestions(@RequestBody Questions question) {
		questionsService.save(question);
		return "true";
	}
}
