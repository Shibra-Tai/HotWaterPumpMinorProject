package com.crm.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.FileEntity;
import com.crm.entities.InstallationDocument;
import com.crm.repositories.FileRepository;
import com.crm.repositories.InstallationDocumentRepository;


@RestController
@CrossOrigin
@RequestMapping("/")
public class ZipController {
	
	@Autowired
	FileRepository file;
	
	@Autowired
	InstallationDocumentRepository install;
	
	@GetMapping("/zip")
	public ResponseEntity<?> downloadZip(@RequestParam("projectId")int projectId)
	{
		FileEntity f=null;
		InstallationDocument i=null;
		try {
			f=file.findByprojectProjectId(projectId).get();
			
			 i=install.findByprojectProjectId(projectId).get();
		}catch(Exception e) {}
		System.out.println("not present");
		
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try(ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream))
		{
			if(f!=null) {
				
			
			if(f.getElectricityBill()!=null)
			addFileToZip(zipOutputStream, f.getElectricityBill(), "ElectricityBill." + f.getElectricityBillExtension().split("/")[1]);
			if(f.getBeforeInstallation()!=null)
			addFileToZip(zipOutputStream, f.getBeforeInstallation(), "BeforeInstallation." + f.getBeforeInstallationExtension().split("/")[1]);			
			if(f.getCurrentHWUnit()!=null)
			addFileToZip(zipOutputStream, f.getCurrentHWUnit(), "CurrentHWUnit." + f.getCurrentHWUnitExtension().split("/")[1]);
			if(f.getCurrentHWUnitCompliancePlate()!=null)
			addFileToZip(zipOutputStream, f.getCurrentHWUnitCompliancePlate(), "CurrentHWUnitCompliancePlate." + f.getCurrentHWUnitCompliancePlateExtension().split("/")[1]);
			if(f.getOutsidePremisesSignage()!=null)
			addFileToZip(zipOutputStream, f.getOutsidePremisesSignage(), "OutsidePremisesSignage." + f.getOutsidePremisesSignageExtension().split("/")[1]);
			if(f.getForm17()!=null)
			addFileToZip(zipOutputStream, f.getForm17(), "Form17.pdf");
			if(f.getForm16()!=null)
			addFileToZip(zipOutputStream, f.getForm16(), "Form16.pdf");
			if(f.getNomination_form()!=null)
			addFileToZip(zipOutputStream, f.getNomination_form(), "NominationForm.pdf");
			}
			if(i!=null) {
			if(i.getExisting_system_in_situ()!=null)
			addFileToZip(zipOutputStream, i.getExisting_system_in_situ(), "ExistingSystemInSitu." + i.getExisting_system_in_situ_extension().split("/")[1]);
			if(i.getExisting_system_compliance_plate()!=null)
			addFileToZip(zipOutputStream, i.getExisting_system_compliance_plate(), "ExistingSystemCompliancePlate." + i.getExisting_system_compliance_plate_extension().split("/")[1]);
			if(i.getExisting_system_decommissioning()!=null)
			addFileToZip(zipOutputStream, i.getExisting_system_decommissioning(), "ExistingSystemDecommissioning." + i.getExisting_system_decommissioning_extension().split("/")[1]);
			if(i.getNew_installed_system_compliance_plate()!=null)
			addFileToZip(zipOutputStream, i.getNew_installed_system_compliance_plate(), "NewInstalledSystemCompliancePlate." + i.getNew_installed_system_compliance_plate_extension().split("/")[1]);
			if(i.getNew_installed_system_situ()!=null)
			addFileToZip(zipOutputStream, i.getNew_installed_system_situ(), "NewInstalledSystemSitu." + i.getNew_installed_system_situ_extension().split("/")[1]);
			if(i.getInstaller_selfie()!=null)
			addFileToZip(zipOutputStream, i.getInstaller_selfie(), "InstallerSelfie." + i.getInstaller_selfie_extension().split("/")[1]);
			if(i.getOustide_premises()!=null)
			addFileToZip(zipOutputStream, i.getOustide_premises(), "OutsidePremises." + i.getOustide_premises_extension().split("/")[1]);
			if(i.getCustomer_sign()!=null)
			addFileToZip(zipOutputStream, i.getCustomer_sign(), "CustomerSign." + i.getCustomer_sign_extension().split("/")[1]);
			}
			zipOutputStream.flush();
			outputStream.flush();
			outputStream.close();
			zipOutputStream.close();
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=projectId" + projectId + ".zip");
			return ResponseEntity.ok().headers(headers).body(outputStream.toByteArray());
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate the zip file");
		}
	}

	private void addFileToZip(ZipOutputStream zipOutputStream, byte[] fileContent, String fileName) throws IOException
	{
		if(fileContent != null)
		{
			ZipEntry zipEntry = new ZipEntry(fileName);
			zipOutputStream.putNextEntry(zipEntry);
			zipOutputStream.write(fileContent);
			zipOutputStream.closeEntry();
		}
	}
}
