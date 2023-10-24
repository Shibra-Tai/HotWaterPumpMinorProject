package com.crm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class UploadQuestions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="project_id", referencedColumnName= "projectId")
	private Project project;
	private String signedNominationFormPast;
	private String buildingType;
	private String occupyByBusiness;
	private String titleOfCustomer;
	private String fullNameOfCustomer;
	private String quotingFor;
	private String typeOldSystem;
	private String replaceAirSource;
	private String solarWaterHeater;
	private String gasReplaceAirSource;
	private String gasSolarWaterHeaterElectricBoosted;
	private String gasSolarWaterHeaterGasBoosted;
	private String paid;
	private String tankSerialNumber;
	public String getTankSerialNumber() {
		return tankSerialNumber;
	}
	public void setTankSerialNumber(String tankSerialNumber) {
		this.tankSerialNumber = tankSerialNumber;
	}
	public String getVolumetricCapacity() {
		return volumetricCapacity;
	}
	public void setVolumetricCapacity(String volumetricCapacity) {
		this.volumetricCapacity = volumetricCapacity;
	}
	private String volumetricCapacity;

//
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
//	
//	
	public String getSignedNominationFormPast() {
		return signedNominationFormPast;
	}
	public void setSignedNominationFormPast(String signedNominationFormPast) {
		this.signedNominationFormPast = signedNominationFormPast;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getOccupyByBusiness() {
		return occupyByBusiness;
	}
	public void setOccupyByBusiness(String occupyByBusiness) {
		this.occupyByBusiness = occupyByBusiness;
	}

//	
//	
//	
//	
//	
	public String getTitleOfCustomer() {
		return titleOfCustomer;
	}
	public void setTitleOfCustomer(String titleOfCustomer) {
		this.titleOfCustomer = titleOfCustomer;
	}
	public String getFullNameOfCustomer() {
		return fullNameOfCustomer;
	}
	public void setFullNameOfCustomer(String fullNameOfCustomer) {
		this.fullNameOfCustomer = fullNameOfCustomer;
	}
	
	
	public String getQuotingFor() {
		return quotingFor;
	}
	public void setQuotingFor(String quotingFor) {
		this.quotingFor = quotingFor;
	}
	public String getTypeOldSystem() {
		return typeOldSystem;
	}
	public void setTypeOldSystem(String typeOldSystem) {
		this.typeOldSystem = typeOldSystem;
	}
	public String getReplaceAirSource() {
		return replaceAirSource;
	}
	public void setReplaceAirSource(String replaceAirSource) {
		this.replaceAirSource = replaceAirSource;
	}
	public String getSolarWaterHeater() {
		return solarWaterHeater;
	}
	public void setSolarWaterHeater(String solarWaterHeater) {
		this.solarWaterHeater = solarWaterHeater;
	}
	public String getGasReplaceAirSource() {
		return gasReplaceAirSource;
	}
	public void setGasReplaceAirSource(String gasReplaceAirSource) {
		this.gasReplaceAirSource = gasReplaceAirSource;
	}
	public String getGasSolarWaterHeaterElectricBoosted() {
		return gasSolarWaterHeaterElectricBoosted;
	}
	public void setGasSolarWaterHeaterElectricBoosted(String gasSolarWaterHeaterElectricBoosted) {
		this.gasSolarWaterHeaterElectricBoosted = gasSolarWaterHeaterElectricBoosted;
	}
	public String getGasSolarWaterHeaterGasBoosted() {
		return gasSolarWaterHeaterGasBoosted;
	}
	public void setGasSolarWaterHeaterGasBoosted(String gasSolarWaterHeaterGasBoosted) {
		this.gasSolarWaterHeaterGasBoosted = gasSolarWaterHeaterGasBoosted;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	
	
}