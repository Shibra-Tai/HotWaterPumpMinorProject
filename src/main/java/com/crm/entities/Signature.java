package com.crm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Signature {
	
	@Id
	private int signId;
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="project_id", referencedColumnName= "projectId")
	private Project project;
	@Lob
	@Column(length = 909715200)
	private byte[] siteacessor;
	@Lob
	@Column(length = 909715200)
	private byte[] customer;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public byte[] getSiteacessor() {
		return siteacessor;
	}
	public void setSiteacessor(byte[] siteacessor) {
		this.siteacessor = siteacessor;
	}
	public byte[] getCustomer() {
		return customer;
	}
	public void setCustomer(byte[] customer) {
		this.customer = customer;
	}
	
}