package com.crm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import com.crm.entities.Project;

@Entity
//@SecondaryTables({
//	@SecondaryTable(name="Project", pkJoinColumns= {
//			@PrimaryKeyJoinColumn(name="projectId", referencedColumnName="projectId")
//			}
//			)
//})
//@IdClass(AddressKey.class)
public class Address 
{
	
	@Id
	private int addressId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="project_id", referencedColumnName= "projectId")
	private Project project;
	private String buildingName;
	private String unitNumber;
	private String lotNumber;
	private String StreetNumber;
	private String StreetName;
	private String StreetTypeSuffix;
	private String suburb;
	private String state;
	private String postcode;
	
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getStreetNumber() {
		return StreetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		StreetNumber = streetNumber;
	}
	public String getStreetName() {
		return StreetName;
	}
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}
	public String getStreetTypeSuffix() {
		return StreetTypeSuffix;
	}
	public void setStreetTypeSuffix(String streetTypeSuffix) {
		StreetTypeSuffix = streetTypeSuffix;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	

		
}
