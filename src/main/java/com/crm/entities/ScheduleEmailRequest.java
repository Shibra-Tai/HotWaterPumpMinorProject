package com.crm.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ScheduleEmailRequest 
{
	
    private final String subject = "Reminder for job";
    
    private String body="Today you have been scheduled a job";
    
    private String email;  //set from database
    
    private LocalDateTime dateTime; 
    
    private final ZoneId timeZone = ZoneId.of("Asia/Kolkata"); 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	

	public String getBody() {
		return body;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public ZoneId getTimeZone() {
		return timeZone;
	}

	
	
//	private String userName; 
//	
//	@Temporal(TemporalType.DATE)
//	private Date scheduleDate;
//	
//	private int projectId;
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public Date getScheduleDate() {
//		return scheduleDate;
//	}
//
//	public void setScheduleDate(Date scheduleDate) {
//		this.scheduleDate = scheduleDate;
//	}

//	public int getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(int projectId) {
//		this.projectId = projectId;
//	}
	
	
	
}
