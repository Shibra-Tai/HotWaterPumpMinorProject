package com.crm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Questions {
	@Id
	private int questionId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="project_id", referencedColumnName= "projectId")
	private Project project;
	@Lob
	private byte[] electricityBill;
	private String electricityBillExtension;
	private String signedNominationFormPast;
	private String buildingType;
	private String occupyByBusiness;
	@Lob
	private byte [] beforeInstallation;
	private String beforeInstallationExtension;
	@Lob
	private byte[] currentHWUnit;
	private String currentHWUnitExtension;
	@Lob
	private byte[] currentHWUnitCompliancePlate;
	private String currentHWUnitCompliancePlateExtension;
	private String titleOfCustomer;
	private String fullNameOfCustomer;
	@Lob
	private byte[] outsidePremisesSignage;
	private String outsidePremisesSignageExtension;
	private String quotingFor;
	private String typeOldSystem;
	private String replaceAirSource;
	private String solarWaterHeater;
	private String gasReplaceAirSource;
	private String gasSolarWaterHeaterElectricBoosted;
	private String gasSolarWaterHeaterGasBoosted;
	private String paid;
	
	
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
	public byte[] getElectricityBill() {
		return electricityBill;
	}
	public void setElectricityBill(byte[] electricityBill) {
		this.electricityBill = electricityBill;
	}
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
	public byte[] getBeforeInstallation() {
		return beforeInstallation;
	}
	public void setBeforeInstallation(byte[] beforeInstallation) {
		this.beforeInstallation = beforeInstallation;
	}
	public byte[] getCurrentHWUnit() {
		return currentHWUnit;
	}
	public void setCurrentHWUnit(byte[] currentHWUnit) {
		this.currentHWUnit = currentHWUnit;
	}
	public byte[] getCurrentHWUnitCompliancePlate() {
		return currentHWUnitCompliancePlate;
	}
	public void setCurrentHWUnitCompliancePlate(byte[] currentHWUnitCompliancePlate) {
		this.currentHWUnitCompliancePlate = currentHWUnitCompliancePlate;
	}
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
	public byte[] getOutsidePremisesSignage() {
		return outsidePremisesSignage;
	}
	public void setOutsidePremisesSignage(byte[] outsidePremisesSignage) {
		this.outsidePremisesSignage = outsidePremisesSignage;
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
	public String getElectricityBillExtension() {
		return electricityBillExtension;
	}
	public void setElectricityBillExtension(String electricityBillExtension) {
		this.electricityBillExtension = electricityBillExtension;
	}
	public String getBeforeInstallationExtension() {
		return beforeInstallationExtension;
	}
	public void setBeforeInstallationExtension(String beforeInstallationExtension) {
		this.beforeInstallationExtension = beforeInstallationExtension;
	}
	public String getCurrentHWUnitExtension() {
		return currentHWUnitExtension;
	}
	public void setCurrentHWUnitExtension(String currentHWUnitExtension) {
		this.currentHWUnitExtension = currentHWUnitExtension;
	}
	public String getCurrentHWUnitCompliancePlateExtension() {
		return currentHWUnitCompliancePlateExtension;
	}
	public void setCurrentHWUnitCompliancePlateExtension(String currentHWUnitCompliancePlateExtension) {
		this.currentHWUnitCompliancePlateExtension = currentHWUnitCompliancePlateExtension;
	}
	public String getOutsidePremisesSignageExtension() {
		return outsidePremisesSignageExtension;
	}
	public void setOutsidePremisesSignageExtension(String outsidePremisesSignageExtension) {
		this.outsidePremisesSignageExtension = outsidePremisesSignageExtension;
	}
	
	
	
}
