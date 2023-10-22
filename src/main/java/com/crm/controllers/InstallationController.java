package com.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crm.entities.InstallationDocument;
import com.crm.entities.Project;
import com.crm.repositories.InstallationDocumentRepository;


@RestController
@RequestMapping("/")
@CrossOrigin
public class InstallationController {
	
	@Autowired
	InstallationDocumentRepository repo;
	
	@PostMapping(value="/upload-installation-document", consumes = {"multipart/form-data" })
	public ResponseEntity<String> uploadInstallation(@RequestParam("exisitingsystemsitu")MultipartFile file1,@RequestParam(name="exisitingsystemcomplianceplate",required=false)MultipartFile file2,@RequestParam(name="exisitingsystemdecommisioning",required=false )MultipartFile file3,@RequestParam(name="newsystem" ,required=false)MultipartFile file4,@RequestParam(name="newsystemsitu",required=false)MultipartFile file5,@RequestParam(name="installerselfie",required=false)MultipartFile file6,@RequestParam(name="outsidepremises",required=false)MultipartFile file7,@RequestParam(name="customerSign",required=false)String file8,@RequestParam (name="installerSign",required=false)String file9,@RequestParam (name="projectId",required=false)String projectId){
		InstallationDocument entity=new InstallationDocument();
		try{
			if(file1!=null) {
				entity.setExisting_system_in_situ(file1.getBytes());
				entity.setExisting_system_in_situ_extension(file1.getContentType());
			}if(file2!=null) {
				entity.setExisting_system_compliance_plate(file2.getBytes());
				entity.setExisting_system_compliance_plate_extension(file2.getContentType());;
			}if(file3!=null) {
				entity.setExisting_system_decommissioning(file3.getBytes());
				entity.setExisting_system_decommissioning_extension(file3.getContentType());
			}if(file4!=null) {
				entity.setNew_installed_system_compliance_plate(file4.getBytes());
				entity.setNew_installed_system_compliance_plate_extension(file4.getContentType());
			}if(file5!=null) {
				entity.setNew_installed_system_situ(file5.getBytes());
				entity.setNew_installed_system_situ_extension(file5.getContentType());
			}if(file6!=null) {
				entity.setInstaller_selfie(file6.getBytes());
				entity.setInstaller_selfie_extension(file6.getContentType());
			}if(file7!=null) {
				entity.setOustide_premises(file7.getBytes());
				entity.setOustide_premises_extension(file7.getContentType());
			}if(file8!=null) {
				entity.setCustomer_sign(java.util.Base64.getDecoder().decode(file8));
				entity.setCustomer_sign_extension("image/png");
			}
			if(file9!=null) {
				entity.setCustomer_sign(java.util.Base64.getDecoder().decode(file9));
				entity.setCustomer_sign_extension("image/png");
			}
			Project p=new Project();
			p.setProjectId(Integer.parseInt(projectId));
			entity.setProject(p);
			repo.save(entity);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false",HttpStatus.OK);
			
		}
		
		return new ResponseEntity<String>("true",HttpStatus.OK);
		
		
	}
	@PostMapping("/download-installation-document/{requiredDocument}")
	public ResponseEntity<?> getInstallationDocument(@PathVariable("requiredDocument")String requiredDocument,@RequestParam("projectId")int projectId){
		InstallationDocument i=repo.findByprojectProjectId(projectId).get();
		if(requiredDocument.equals("existing_system_in_situ")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getExisting_system_in_situ_extension()))
				.body(i.getExisting_system_in_situ());
		}
		else if(requiredDocument.equals("existing_system_compliance_plate")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getExisting_system_compliance_plate_extension()))
				.body(i.getExisting_system_compliance_plate());
		}else if(requiredDocument.equals("existing_system_decommissioning")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getExisting_system_decommissioning_extension()))
				.body(i.getExisting_system_decommissioning());
		}
		else if(requiredDocument.equals("new_installed_system_compliance_plate")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getNew_installed_system_compliance_plate_extension()))
				.body(i.getNew_installed_system_compliance_plate());
		}
		else if(requiredDocument.equals("new_installed_system_situ")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getNew_installed_system_situ_extension()))
				.body(i.getNew_installed_system_situ());
		}
		else if(requiredDocument.equals("oustide_premises")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getOustide_premises_extension()))
				.body(i.getOustide_premises());
		}
		else if(requiredDocument.equals("customer_sign")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getCustomer_sign_extension()))
				.body(i.getCustomer_sign());
		}
		else if(requiredDocument.equals("installer_selfie")) {
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf(i.getInstaller_selfie_extension()))
				.body(i.getInstaller_selfie());
		}
		else {
			return new ResponseEntity<String>("not found",HttpStatus.OK);

		}
	}

	
	
}