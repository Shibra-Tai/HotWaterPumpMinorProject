package com.crm.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Schedule 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int scheduleId;
	
	private int projectId;
	
	private int userIdOfInstaller;
	
	private int userIdOfSalesman;
	
	@Temporal(TemporalType.DATE)
	private Date scheduleDate; 
	//Format: 'YYYY-MM-DD'
	
	
	public Schedule()
	{
		
	}


	public Schedule(int scheduleId, int projectId, int userIdOfInstaller, int userIdOfSalesman, Date scheduleDate) {
		super();
		this.scheduleId = scheduleId;
		this.projectId = projectId;
		this.userIdOfInstaller = userIdOfInstaller;
		this.userIdOfSalesman = userIdOfSalesman;
		this.scheduleDate = scheduleDate;
	}


	public int getScheduleId() {
		return scheduleId;
	}


	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}


	public int getProjectId() {
		return projectId;
	}


	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public int getUserIdOfInstaller() {
		return userIdOfInstaller;
	}


	public void setUserIdOfInstaller(int userIdOfInstaller) {
		this.userIdOfInstaller = userIdOfInstaller;
	}


	public int getUserIdOfSalesman() {
		return userIdOfSalesman;
	}


	public void setUserIdOfSalesman(int userIdOfSalesman) {
		this.userIdOfSalesman = userIdOfSalesman;
	}


	public Date getScheduleDate() {
		return scheduleDate;
	}


	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	
	
	

}
