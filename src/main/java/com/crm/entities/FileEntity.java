package com.crm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class FileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fid;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="project_id", referencedColumnName= "projectId")
	private Project project;
	@Lob
	@Column(length = 999999999)
	private byte[] electricityBill;
	private String electricityBillExtension;	
	@Lob
	@Column(length = 909715200)

	private byte [] beforeInstallation;
	private String beforeInstallationExtension;
	@Lob
	@Column(length = 909715200)

	private byte[] currentHWUnit;
	private String currentHWUnitExtension;
	@Lob
	@Column(length = 909715200)

	private byte[] currentHWUnitCompliancePlate;
	private String currentHWUnitCompliancePlateExtension;
	
	@Lob
	@Column(length = 909715200)

	private byte[] outsidePremisesSignage;
	private String outsidePremisesSignageExtension;

	
	@Lob
	
	private byte[] form17;
	@Lob
	private byte[] form16;
	@Lob
	private byte[] nomination_form;

	@Lob
	private byte[] stc_form;
	
	
	
	public byte[] getStc_form() {
		return stc_form;
	}
	public void setStc_form(byte[] stc_form) {
		this.stc_form = stc_form;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
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
	public String getElectricityBillExtension() {
		return electricityBillExtension;
	}
	public void setElectricityBillExtension(String electricityBillExtension) {
		this.electricityBillExtension = electricityBillExtension;
	}
	public byte[] getBeforeInstallation() {
		return beforeInstallation;
	}
	public void setBeforeInstallation(byte[] beforeInstallation) {
		this.beforeInstallation = beforeInstallation;
	}
	public String getBeforeInstallationExtension() {
		return beforeInstallationExtension;
	}
	public void setBeforeInstallationExtension(String beforeInstallationExtension) {
		this.beforeInstallationExtension = beforeInstallationExtension;
	}
	public byte[] getCurrentHWUnit() {
		return currentHWUnit;
	}
	public void setCurrentHWUnit(byte[] currentHWUnit) {
		this.currentHWUnit = currentHWUnit;
	}
	public String getCurrentHWUnitExtension() {
		return currentHWUnitExtension;
	}
	public void setCurrentHWUnitExtension(String currentHWUnitExtension) {
		this.currentHWUnitExtension = currentHWUnitExtension;
	}
	public byte[] getCurrentHWUnitCompliancePlate() {
		return currentHWUnitCompliancePlate;
	}
	public void setCurrentHWUnitCompliancePlate(byte[] currentHWUnitCompliancePlate) {
		this.currentHWUnitCompliancePlate = currentHWUnitCompliancePlate;
	}
	public String getCurrentHWUnitCompliancePlateExtension() {
		return currentHWUnitCompliancePlateExtension;
	}
	public void setCurrentHWUnitCompliancePlateExtension(String currentHWUnitCompliancePlateExtension) {
		this.currentHWUnitCompliancePlateExtension = currentHWUnitCompliancePlateExtension;
	}
	public byte[] getOutsidePremisesSignage() {
		return outsidePremisesSignage;
	}
	public void setOutsidePremisesSignage(byte[] outsidePremisesSignage) {
		this.outsidePremisesSignage = outsidePremisesSignage;
	}
	public String getOutsidePremisesSignageExtension() {
		return outsidePremisesSignageExtension;
	}
	public void setOutsidePremisesSignageExtension(String outsidePremisesSignageExtension) {
		this.outsidePremisesSignageExtension = outsidePremisesSignageExtension;
	}
	public byte[] getForm17() {
		return form17;
	}
	public void setForm17(byte[] form17) {
		this.form17 = form17;
	}
	public byte[] getForm16() {
		return form16;
	}
	public void setForm16(byte[] form16) {
		this.form16 = form16;
	}
	public byte[] getNomination_form() {
		return nomination_form;
	}
	public void setNomination_form(byte[] nomination_form) {
		this.nomination_form = nomination_form;
	}
	
	
}