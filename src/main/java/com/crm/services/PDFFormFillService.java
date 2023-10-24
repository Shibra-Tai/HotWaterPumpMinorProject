package com.crm.services;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Address;
import com.crm.entities.Project;
import com.crm.entities.Questions;
import com.crm.entities.Schedule;
import com.crm.entities.UploadQuestions;
import com.crm.entities.User;
import com.crm.repositories.AddressRepository;
import com.crm.repositories.FileRepository;
import com.crm.repositories.InstallationDocumentRepository;
import com.crm.repositories.ProjectRepository;
import com.crm.repositories.QuestionsRepository;
import com.crm.repositories.ScheduleRepository;
import com.crm.repositories.SignatureRepository;
import com.crm.repositories.UploadQuestionsRepository;
import com.crm.repositories.UserRepository;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;

@Service
public class PDFFormFillService {

	@Autowired
	ProjectRepository proj;

	@Autowired
	UploadQuestionsRepository que;
	
	@Autowired
	AddressRepository addr;

	@Autowired
	SignatureRepository sign;
	
	@Autowired
	InstallationDocumentRepository inst;
	
	@Autowired
	ScheduleRepository sch;
	
	@Autowired
	FileRepository file;

	@Autowired
	UserRepository user;

	public boolean form17(int projectId) {
		try (PDDocument document = Loader.loadPDF(new File(
				"\\crm\\src\\main\\java\\com\\crm\\form\\nomination_form.pdf"))) {
			PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
			Project p = proj.findById(projectId).get();
			UploadQuestions q = que.findByprojectProjectId(projectId).get();
			acroForm.getField("name").setValue(p.getCustomerFirstName() + " " + p.getCustomerLastName());
			acroForm.getField("position").setValue(q.getTitleOfCustomer());
			acroForm.getField("company").setValue(p.getCompanyName());
			acroForm.getField("date").setValue("" + LocalDate.now());
			acroForm.getField("signedBy").setValue("Choice2");
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] signatureImage = sign.findById(projectId).get().getSiteacessor();
			PDField signatureField = acroForm.getField("signature");
			PDImageXObject imageXObject = PDImageXObject.createFromByteArray(document, signatureImage,
					"SignatureImage");
			PDField field = acroForm.getField("signature");
			PDAnnotation widget = field.getWidgets().get(0);
			PDPage page = widget.getPage();
			float imageWidth = imageXObject.getWidth();
			float imageHeight = imageXObject.getHeight();
			float fieldWidth = widget.getRectangle().getWidth();
			float fieldHeight = widget.getRectangle().getHeight();
			float x = widget.getRectangle().getLowerLeftX();
			float y = widget.getRectangle().getLowerLeftY();
			PDPageContentStream contentStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true);
			contentStream.drawImage(imageXObject, x, y, fieldWidth, fieldHeight);
			contentStream.close();
			acroForm.flatten();
			document.save(byteArrayOutputStream);
			document.close();
			byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
			com.crm.entities.FileEntity f=file.findById(projectId).get();
			f.setForm17(modifiedPdfBytes);
//			que.save(q);
			file.save(f);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean form16(int projectId) {
		try (PDDocument document = Loader.loadPDF(new File(
				"\\crm\\src\\main\\java\\com\\crm\\form\\form16.pdf"))) {
			PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
			Project p = proj.findById(projectId).get();
			UploadQuestions q = que.findByprojectProjectId(projectId).get();
			acroForm.getField("name").setValue(p.getCustomerFirstName() + " " + p.getCustomerLastName());
			acroForm.getField("position").setValue(q.getTitleOfCustomer());
			acroForm.getField("company").setValue(p.getCompanyName());
			acroForm.getField("date").setValue("" + LocalDate.now());
			acroForm.getField("signedBy").setValue("Choice2");
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] signatureImage = sign.findById(projectId).get().getSiteacessor();
			PDField signatureField = acroForm.getField("signature");
			PDImageXObject imageXObject = PDImageXObject.createFromByteArray(document, signatureImage,
					"SignatureImage");
			PDField field = acroForm.getField("signature");
			PDAnnotation widget = field.getWidgets().get(0);
			PDPage page = widget.getPage();
			float imageWidth = imageXObject.getWidth();
			float imageHeight = imageXObject.getHeight();
			float fieldWidth = widget.getRectangle().getWidth();
			float fieldHeight = widget.getRectangle().getHeight();
			float x = widget.getRectangle().getLowerLeftX();
			float y = widget.getRectangle().getLowerLeftY();
			PDPageContentStream contentStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true);
			contentStream.drawImage(imageXObject, x, y, fieldWidth, fieldHeight);
			contentStream.close();
			acroForm.flatten();
			document.save(byteArrayOutputStream);
			document.close();
			byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
			com.crm.entities.FileEntity f=file.findById(projectId).get();
			f.setForm16(modifiedPdfBytes);
//			que.save(q);
			file.save(f);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public boolean nominationform(int projectId) {
		
		//Description is remaining 
		try (PDDocument document = Loader.loadPDF(new File(
				"\\crm\\src\\main\\java\\com\\crm\\form\\form16.pdf"))) {
			PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
			Project p = proj.findById(projectId).get();
			UploadQuestions q = que.findByprojectProjectId(projectId).get();
			acroForm.getField("name").setValue(p.getCompanyName());
			acroForm.getField("abn").setValue(""+p.getAbnNumber());
			acroForm.getField("email").setValue(p.getEmailId());
			acroForm.getField("date").setValue("" + LocalDate.now());
			acroForm.getField("phone").setValue(""+p.getCustomerPhoneNumber());
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] signatureImage = sign.findById(projectId).get().getCustomer();
			PDField signatureField = acroForm.getField("signature");
			PDImageXObject imageXObject = PDImageXObject.createFromByteArray(document, signatureImage,
					"SignatureImage");
			PDField field = acroForm.getField("signature");
			PDAnnotation widget = field.getWidgets().get(0);
			PDPage page = widget.getPage();
			float imageWidth = imageXObject.getWidth();
			float imageHeight = imageXObject.getHeight();
			float fieldWidth = widget.getRectangle().getWidth();
			float fieldHeight = widget.getRectangle().getHeight();
			float x = widget.getRectangle().getLowerLeftX();
			float y = widget.getRectangle().getLowerLeftY();
			PDPageContentStream contentStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true);
			contentStream.drawImage(imageXObject, x, y, fieldWidth, fieldHeight);
			contentStream.close();
			acroForm.flatten();
			document.save(byteArrayOutputStream);
			document.close();
			byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
			com.crm.entities.FileEntity f=file.findById(projectId).get();
			f.setNomination_form(modifiedPdfBytes);
			file.save(f);
//			que.save(q);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	public boolean stcForm(int projectId) {
		try (PDDocument document = Loader.loadPDF(new File(
				"\\crm\\src\\main\\java\\com\\crm\\form\\stcForm.pdf"))) {
			PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
			Project p = proj.findById(projectId).get();
			Address a=addr.findByprojectProjectId(projectId).get();
			UploadQuestions q = que.findByprojectProjectId(projectId).get();
			Schedule s=sch.findByprojectProjectId(projectId).get();
			User u=user.findById(s.getUserIdOfInstaller()).get();

			acroForm.getField("FirstName").setValue(p.getCustomerFirstName());
			acroForm.getField("LastName").setValue(p.getCustomerLastName());
			acroForm.getField("Address").setValue(a.getLotNumber()+""+a.getBuildingName()+""+a.getStreetName()+""+a.getStreetNumber());
			acroForm.getField("Suburb").setValue(a.getSuburb());
			acroForm.getField("PostCode").setValue(a.getPostcode());
			acroForm.getField("Phone").setValue(""+p.getCustomerPhoneNumber());
			acroForm.getField("Mobile").setValue(""+p.getCustomerMobileNumber());
			acroForm.getField("EmailId").setValue(p.getEmailId());
//			acroForm.getField("InstallationAddress").setValue(a.get);
			acroForm.getField("SystemBrand").setValue(p.getSystemBrand());
			acroForm.getField("TankSerialNumber").setValue(q.getTankSerialNumber());
			acroForm.getField("VolumetricCapacity").setValue(q.getVolumetricCapacity());
			acroForm.getField("GWS").setValue("");
			
			acroForm.getField("BuildingType").setValue(p.getCustomerType());
			acroForm.getField("InstallationCompany").setValue(u.getCompanyName());
			acroForm.getField("CompanyAddress").setValue(u.getCompanyAddress());
			acroForm.getField("InstallerName").setValue(u.getUserName());
			acroForm.getField("InstallerPhoneNumber").setValue(u.getPhoneNumber());
			acroForm.getField("InstallerEmailId").setValue(u.getUserEmail());
			acroForm.getField("OwnerName").setValue(p.getCustomerFirstName()+""+p.getCustomerLastName());
			acroForm.getField("Date").setValue("" + LocalDate.now());
			
				
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			
			// need To change
//			byte[] instsignatureImage = inst.findByprojectProjectId(projectId).get().getCustomer_sign();
			byte[] custsignatureImage = inst.findByprojectProjectId(projectId).get().getCustomer_sign();
			// PDField signatureField = acroForm.getField("OwnerSign");
			PDImageXObject imageXObject = PDImageXObject.createFromByteArray(document, custsignatureImage,
					"SignatureImage");
			PDField field = acroForm.getField("OwnerSign");
			PDAnnotation widget = field.getWidgets().get(0);
			PDPage page = widget.getPage();
			// float imageWidth = imageXObject.getWidth();
			// float imageHeight = imageXObject.getHeight();
			float fieldWidth = widget.getRectangle().getWidth();
			float fieldHeight = widget.getRectangle().getHeight();
			float x = widget.getRectangle().getLowerLeftX();
			float y = widget.getRectangle().getLowerLeftY();
			PDPageContentStream contentStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true);
			contentStream.drawImage(imageXObject, x, y, fieldWidth, fieldHeight);
			contentStream.close();
			
			
			//  signatureField = acroForm.getField("InstallerSign");
			 imageXObject = PDImageXObject.createFromByteArray(document, custsignatureImage,
					"SignatureImage");
			 field = acroForm.getField("InstallerSign");
			widget = field.getWidgets().get(0);
			 page = widget.getPage();
			//  imageWidth = imageXObject.getWidth();
			// imageHeight = imageXObject.getHeight();
			 fieldWidth = widget.getRectangle().getWidth();
			fieldHeight = widget.getRectangle().getHeight();
			 x = widget.getRectangle().getLowerLeftX();
			y = widget.getRectangle().getLowerLeftY();
			 contentStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true);
			contentStream.drawImage(imageXObject, x, y, fieldWidth, fieldHeight);
			contentStream.close();
			
			
			acroForm.flatten();
			document.save(byteArrayOutputStream);
			document.close();
			byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
			com.crm.entities.FileEntity f=file.findById(projectId).get();
			f.setStc_form(modifiedPdfBytes);
			//que.save(q);
			file.save(f);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}