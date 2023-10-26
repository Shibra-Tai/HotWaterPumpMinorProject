package com.crm.controllers;

import java.io.IOException;
import java.util.Base64.*;


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


import com.crm.entities.Signature;
import com.crm.repositories.ProjectRepository;
import com.crm.repositories.SignatureRepository;
import com.crm.services.PDFFormFillService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class SignatureController {
	@Autowired
	SignatureRepository sign;
	@Autowired
	ProjectRepository proj;

	@Autowired
	PDFFormFillService pdf;
	
	@PostMapping("/sign")
	public boolean uploadSign(@RequestParam(name="siteacessor",required=false)String sign1,@RequestParam(name="siteacessor",required=false)String sign2,@RequestParam("projectId")int projectId)
	{
		Signature s=new Signature();
		try {
			
			s.setCustomer(java.util.Base64.getDecoder().decode(sign2));
			s.setSiteacessor(java.util.Base64.getDecoder().decode(sign1));
			s.setProject(proj.findById(projectId).get());
			sign.save(s);
			pdf.form16(projectId);
			pdf.form17(projectId);
			pdf.nominationform(projectId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	@PostMapping("/getsign/{signature}")
	public ResponseEntity<?> getsign(@PathVariable("signature")String signature,@RequestParam("projectId")int projectId) {
		Signature s= sign.findByprojectProjectId(projectId).get();
		byte[]ans = null;
		if(signature.equals("siteacessor")) {
			ans=s.getSiteacessor();
		}
		if(signature.equals("customer")) {
			ans=s.getCustomer();
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(ans);
		
	}
	
}