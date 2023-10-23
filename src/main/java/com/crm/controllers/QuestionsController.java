package com.crm.controllers;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crm.entities.FileEntity;
import com.crm.entities.Project;
import com.crm.entities.UploadQuestions;
import com.crm.repositories.FileRepository;
import com.crm.repositories.UploadQuestionsRepository;
import com.crm.services.QuestionsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/")
public class QuestionsController {
	
	@Autowired
	QuestionsService serv;
	
	@Autowired
	FileRepository repo;
	
	@Autowired
	UploadQuestionsRepository repo2;
	
	
	@PostMapping(value="/save-question", consumes = {"multipart/form-data" })
	public ResponseEntity<String> saveQuestions(@RequestParam(name="projectId")String projectId,@RequestParam(name="electricityBill") MultipartFile file1,@RequestParam(name="beforeInstallation",required=false) MultipartFile file2,@RequestParam(name="currentHWUnit",required=false) MultipartFile file3,@RequestParam(name="currentHWUnitCompliancePlate" ,required=false) MultipartFile file4,@RequestParam(name="outsidePremisesSignage" ,required=false) MultipartFile file5,@RequestParam(name="uploadQuestions") String que) {
		FileEntity f=new FileEntity();
		try {
//			q.setProject(que.getProject());
			if(file1!=null) {
				System.out.println(file1.getBytes().length);

				f.setElectricityBill(file1.getBytes());
				System.out.println(f.getElectricityBill().length);
				f.setElectricityBillExtension(file1.getContentType());
			}
			if(file2!=null) {
				f.setBeforeInstallation(file2.getBytes());
				f.setBeforeInstallationExtension(file2.getContentType());
			}
			if(file3!=null) {
				f.setCurrentHWUnit(file3.getBytes());
				f.setCurrentHWUnitExtension(file3.getContentType());
			}if(file4!=null) {
				f.setCurrentHWUnitCompliancePlate(file4.getBytes());
				f.setCurrentHWUnitCompliancePlateExtension(file4.getContentType());
			}if(file5!=null) {
				f.setOutsidePremisesSignage(file5.getBytes());
				f.setOutsidePremisesSignageExtension(file5.getContentType());
			}
			ObjectMapper mapper=new ObjectMapper();
			UploadQuestions obj2=mapper.readValue(que, UploadQuestions.class);
			
			Project p=new Project();
			p.setProjectId(Integer.parseInt(projectId));
			f.setProject(p);
			System.out.println(obj2);

			repo.save(f);
			
			obj2.setProject(p);
			System.out.print(projectId);
			if(obj2!=null)
				repo2.save(obj2);
			
			

		} catch (IOException e) {
			
			e.printStackTrace();
			return  new ResponseEntity<String>("DATABASE_ERROR",HttpStatus.OK);
		}
		
		
		return  new ResponseEntity<String>("TRUE",HttpStatus.OK);

	}

	@PostMapping("/questions")
	public ResponseEntity<UploadQuestions> getQuestions(@RequestParam("projectId")int projectId){
		try {
		return new ResponseEntity<UploadQuestions>(repo2.findByprojectProjectId(projectId).get(),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<UploadQuestions>(new UploadQuestions(),HttpStatus.OK);

		}
	}
	
	@GetMapping("/questions/{document}")
	public ResponseEntity<?> getDocument(@PathVariable("document")String document,@RequestParam("projectId")int projectId){
		try {
		FileEntity f=repo.findByprojectProjectId(projectId).get();
		byte[]ans;
		String ext;
		if(document.equals("electricitybill")) {
			ans=f.getElectricityBill();
			ext=f.getElectricityBillExtension();
		}else if(document.equals("beforeinstallation")) {
			 ans=f.getBeforeInstallation();
			 ext=f.getBeforeInstallationExtension();
		}else if(document.equals("currenthwunit")) {
			 ans=f.getCurrentHWUnit();
			 ext=f.getCurrentHWUnitExtension();
		}else if(document.equals("currenthwunitcomplianceplate")) {
			 ans=f.getCurrentHWUnitCompliancePlate();
			 ext=f.getCurrentHWUnitCompliancePlateExtension();
		}else if(document.equals("outsidepremisessignage")) {
			 ans=f.getOutsidePremisesSignage();
			 ext=f.getOutsidePremisesSignageExtension();
		}else if(document.equals("form17")) {
			 ans=f.getForm17();
			 ext="application/pdf";
		}else if(document.equals("form16")) {
			 ans=f.getForm16();
			 ext="application/pdf";
		}else if(document.equals("nominationform")) {
			 ans=f.getNomination_form();
			 ext="application/pdf";
		}else{
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		if(ans==null || ext==null) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}			
		String FilePath="\\crm\\src\\main\\resources\\templates\\";
		java.io.File file = new java.io.File(FilePath+"temp."+ext.split("/")[1]);
		  FileUtils.writeByteArrayToFile(file, ans);
		  InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
		  
		  HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Disposition",
					String.format("attachment; filename=\"%s\"", document+"."+ext.split("/")[1]));
			httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
			httpHeaders.add("Pragma", "no-cache");
			httpHeaders.add("Expires", "0");
			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(httpHeaders)
					.contentLength(ans.length)
					.contentType(MediaType.parseMediaType(ext)).body(inputStreamResource);
		return responseEntity;
		}catch(Exception e) {
			// System.out.println(e);
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	
	}	}
}