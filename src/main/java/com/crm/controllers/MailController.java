package com.crm.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.Mail;
import com.crm.services.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")
public class MailController {
	
	@Autowired
	MailService service;
	
	@PostMapping("/sendMail")
	public boolean sendMail(@RequestBody Mail mail) {
			String from="";
			String pass="";
		return service.sendEmail(mail.getTo(), mail.getMessage(), mail.getSubject(),from,pass);
	}
}