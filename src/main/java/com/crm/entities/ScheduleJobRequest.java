package com.crm.entities;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ScheduleJobRequest 
{
	String userNameOfInstaller;
	
	String userNameOfSalesman;
	
	int projectId;
	
	
	@Temporal(TemporalType.DATE)
	Date scheduleDate;
	//Format: 'YYYY-MM-DD'

	
	public ScheduleJobRequest() {
	}

	
	public ScheduleJobRequest(String userNameOfInstaller, String userNameOfSalesman, int projectId, Date scheduleDate) {
		super();
		this.userNameOfInstaller = userNameOfInstaller;
		this.userNameOfSalesman = userNameOfSalesman;
		this.projectId = projectId;
		this.scheduleDate = scheduleDate;
	}

	
	public String getUserNameOfInstaller() {
		return userNameOfInstaller;
	}
	
	public void setUserNameOfInstaller(String userNameOfInstaller) {
		this.userNameOfInstaller = userNameOfInstaller;
	}


	public String getUserNameOfSalesman() {
		return userNameOfSalesman;
	}


	public void setUserNameOfSalesman(String userNameOfSalesman) {
		this.userNameOfSalesman = userNameOfSalesman;
	}


	public int getProjectId() {
		return projectId;
	}


	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public Date getScheduleDate() {
		return scheduleDate;
	}


	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	
	

}
