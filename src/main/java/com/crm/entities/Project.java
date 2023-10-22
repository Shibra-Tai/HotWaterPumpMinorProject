package com.crm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Project 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId; 
	private String projectTitle;
	private String customerFirstName;
	private String customerLastName;  
	private long customerPhoneNumber;   
	private long customerMobileNumber;
	private String customerType;
	private String companyName;
	private long abnNumber;
	private String gstRegistered;
	private String emailId;
	
	

//	@OneToOne(mappedBy="project")
//	private Address address;  //  because Address class has a foreign key - projectId from this Entity

		public String getEmailId() {
			return emailId;
		}



		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}


	public int getProjectId() {
		return projectId;
	}



	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}



	public String getProjectTitle() {
		return projectTitle;
	}



	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}



	public String getCustomerFirstName() {
		return customerFirstName;
	}



	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}



	public String getCustomerLastName() {
		return customerLastName;
	}



	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}



	public long getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}



	public void setCustomerPhoneNumber(long customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}



	public long getCustomerMobileNumber() {
		return customerMobileNumber;
	}



	public void setCustomerMobileNumber(long customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}



	public String getCustomerType() {
		return customerType;
	}



	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public long getAbnNumber() {
		return abnNumber;
	}



	public void setAbnNumber(long abnNumber) {
		this.abnNumber = abnNumber;
	}



	public String getGstRegistered() {
		return gstRegistered;
	}



	public void setGstRegistered(String gstRegistered) {
		this.gstRegistered = gstRegistered;
	}



//	public Address getAddress() {
//		return address;
//	}
//
//
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}


	

	
	

}