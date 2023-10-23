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
public class InstallationDocument {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int iid;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="project_id", referencedColumnName= "projectId")
	private Project project;
	@Lob
	@Column(length = 909715200)

	private byte[] existing_system_in_situ;
	private String existing_system_in_situ_extension;
	@Lob
	@Column(length = 909715200)

	private byte[]  existing_system_compliance_plate;
	private String	existing_system_compliance_plate_extension;
	@Lob
	@Column(length = 909715200)

	private byte[] existing_system_decommissioning;
	private String existing_system_decommissioning_extension;
	@Lob
	@Column(length = 909715200)

	private byte[] new_installed_system_compliance_plate;
	private String new_installed_system_compliance_plate_extension;
	@Lob
	@Column(length = 909715200)

	private byte[] new_installed_system_situ;
	private String new_installed_system_situ_extension;
	@Lob
	@Column(length = 909715200)

	private byte[] installer_selfie;
	private String installer_selfie_extension;
	@Lob
	@Column(length = 909715200)

	private byte[] oustide_premises;
	private String oustide_premises_extension;
	@Lob
	@Column(length = 909715200)

	private byte[] customer_sign;
	private String customer_sign_extension;
	@Lob
	@Column(length = 909715200)
	private byte[] installer_sign;
	private String installer_sign_extension;



	
	public String getExisting_system_in_situ_extension() {
		return existing_system_in_situ_extension;
	}
	public void setExisting_system_in_situ_extension(String existing_system_in_situ_extension) {
		this.existing_system_in_situ_extension = existing_system_in_situ_extension;
	}
	public String getExisting_system_compliance_plate_extension() {
		return existing_system_compliance_plate_extension;
	}
	public void setExisting_system_compliance_plate_extension(String existing_system_compliance_plate_extension) {
		this.existing_system_compliance_plate_extension = existing_system_compliance_plate_extension;
	}
	public String getExisting_system_decommissioning_extension() {
		return existing_system_decommissioning_extension;
	}
	public void setExisting_system_decommissioning_extension(String existing_system_decommissioning_extension) {
		this.existing_system_decommissioning_extension = existing_system_decommissioning_extension;
	}
	public String getNew_installed_system_compliance_plate_extension() {
		return new_installed_system_compliance_plate_extension;
	}
	public void setNew_installed_system_compliance_plate_extension(String new_installed_system_compliance_plate_extension) {
		this.new_installed_system_compliance_plate_extension = new_installed_system_compliance_plate_extension;
	}
	public String getNew_installed_system_situ_extension() {
		return new_installed_system_situ_extension;
	}
	public void setNew_installed_system_situ_extension(String new_installed_system_situ_extension) {
		this.new_installed_system_situ_extension = new_installed_system_situ_extension;
	}
	public String getInstaller_selfie_extension() {
		return installer_selfie_extension;
	}
	public void setInstaller_selfie_extension(String installer_selfie_extension) {
		this.installer_selfie_extension = installer_selfie_extension;
	}
	public String getOustide_premises_extension() {
		return oustide_premises_extension;
	}
	public void setOustide_premises_extension(String oustide_premises_extension) {
		this.oustide_premises_extension = oustide_premises_extension;
	}
	public String getCustomer_sign_extension() {
		return customer_sign_extension;
	}
	public void setCustomer_sign_extension(String customer_sign_extension) {
		this.customer_sign_extension = customer_sign_extension;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public byte[] getExisting_system_in_situ() {
		return existing_system_in_situ;
	}
	public void setExisting_system_in_situ(byte[] existing_system_in_situ) {
		this.existing_system_in_situ = existing_system_in_situ;
	}
	public byte[] getExisting_system_compliance_plate() {
		return existing_system_compliance_plate;
	}
	public void setExisting_system_compliance_plate(byte[] existing_system_compliance_plate) {
		this.existing_system_compliance_plate = existing_system_compliance_plate;
	}
	public byte[] getExisting_system_decommissioning() {
		return existing_system_decommissioning;
	}
	public void setExisting_system_decommissioning(byte[] existing_system_decommissioning) {
		this.existing_system_decommissioning = existing_system_decommissioning;
	}
	public byte[] getNew_installed_system_compliance_plate() {
		return new_installed_system_compliance_plate;
	}
	public void setNew_installed_system_compliance_plate(byte[] new_installed_system_compliance_plate) {
		this.new_installed_system_compliance_plate = new_installed_system_compliance_plate;
	}
	public byte[] getNew_installed_system_situ() {
		return new_installed_system_situ;
	}
	public void setNew_installed_system_situ(byte[] new_installed_system_situ) {
		this.new_installed_system_situ = new_installed_system_situ;
	}
	public byte[] getInstaller_selfie() {
		return installer_selfie;
	}
	public void setInstaller_selfie(byte[] installer_selfie) {
		this.installer_selfie = installer_selfie;
	}
	public byte[] getOustide_premises() {
		return oustide_premises;
	}
	public void setOustide_premises(byte[] oustide_premises) {
		this.oustide_premises = oustide_premises;
	}
	public byte[] getCustomer_sign() {
		return customer_sign;
	}
	public void setCustomer_sign(byte[] customer_sign) {
		this.customer_sign = customer_sign;
	}
	public byte[] getInstaller_sign() {
		return installer_sign;
	}
	public void setInstaller_sign(byte[] installer_sign) {
		this.installer_sign = installer_sign;
	}
	public String getInstaller_sign_extension() {
		return installer_sign_extension;
	}
	public void setInstaller_sign_extension(String installer_sign_extension) {
		this.installer_sign_extension = installer_sign_extension;
	}

	
}