package com.crm.entities;

public class CSVFileContent 
{
	private Project project;
	private Address address;
	private Schedule schedule;
	private UploadQuestions uploadQuestions;
	private InstallationDocument installationDocument;
	private FileEntity file;
	private Signature signature;
	
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public UploadQuestions getUploadQuestions() {
		return uploadQuestions;
	}
	public void setUploadQuestions(UploadQuestions uploadQuestions) {
		this.uploadQuestions = uploadQuestions;
	}
	public InstallationDocument getInstallationDocument() {
		return installationDocument;
	}
	public void setInstallationDocument(InstallationDocument installationDocument) {
		this.installationDocument = installationDocument;
	}
	public FileEntity getFile() {
		return file;
	}
	public void setFile(FileEntity file) {
		this.file = file;
	}
	public Signature getSignature() {
		return signature;
	}
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
	
	
	
	

}
