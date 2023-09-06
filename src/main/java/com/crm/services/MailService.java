package com.crm.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender sender;


	public boolean sendEmail(String to, String message, String subject,String from,String password){
		try 
		{
			MimeMessage msg=sender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(msg,true);
			helper.setFrom(from);
			helper.setCc(to);
			helper.setSubject(subject);
			helper.setSentDate(new Date());
			helper.setText(message);
			
			sender.send(msg);
		}
		catch (Exception e) 
		{
			
		}
		return true;
	}
	
}