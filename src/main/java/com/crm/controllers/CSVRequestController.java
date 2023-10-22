package com.crm.controllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.Address;
import com.crm.entities.CSVFileContent;
import com.crm.entities.FileEntity;
import com.crm.entities.InstallationDocument;
import com.crm.entities.Project;
import com.crm.entities.Schedule;
import com.crm.entities.Signature;
import com.crm.entities.UploadQuestions;
import com.crm.exception.ExceptionResponse;
import com.crm.repositories.ProjectRepository;
import com.crm.services.AddressService;
import com.crm.services.FileService;
import com.crm.services.InstallationDocumentService;
import com.crm.services.ProjectService;
import com.crm.services.ScheduleService;
import com.crm.services.SignatureService;
import com.crm.services.UploadQuestionsService;
import com.crm.services.WriteCSVService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;



@RestController
@RequestMapping("/")
public class CSVRequestController 
{
	@Autowired
	AddressService addressService;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	UploadQuestionsService uploadQuestionsService;
	
	@Autowired
	FileService fileService;
	
	
	@Autowired
	InstallationDocumentService installationDocumentService;
	
	@Autowired
	SignatureService signatureService;
	
	WriteCSVService writeCSVService = null;
	
	public String csvFilePath = "C:\\SpringBootProject\\CRM\\crm\\src\\main\\resources\\templates";
	
	@GetMapping("/getcsvfile/{projectId}")
	public ResponseEntity<Object> getCSVContentForId(@PathVariable int projectId) throws IOException
	{
		
		// First get all objects
		Address address = addressService.findAddressByProjectId(projectId);
		Project project = address.getProject();
		Schedule schedule = scheduleService.getByProjectId(projectId);
		UploadQuestions uploadQuestions = uploadQuestionsService.getByProjectId(projectId);
		FileEntity file = fileService.getByProjectId(projectId);
		InstallationDocument installationDocument = installationDocumentService.getByProjectId(projectId);
		Signature signature = signatureService.getByProjectId(projectId);
		
		
		// Form this object that contains all other objects that are to be written to CSV
		CSVFileContent csvFileContent = new CSVFileContent();
		csvFileContent.setAddress(address);
		csvFileContent.setProject(project);
		csvFileContent.setSchedule(schedule);
		csvFileContent.setUploadQuestions(uploadQuestions);
		csvFileContent.setFile(file);
		csvFileContent.setInstallationDocument(installationDocument);
		csvFileContent.setSignature(signature);
		
		
		// Now use WriteCSVService to get csv file
		writeCSVService = new WriteCSVService();
		boolean isFileReady = writeCSVService.returnCSVForGivenProjectId(csvFilePath + "//csv_for_id_" + projectId + ".csv", csvFileContent);
		
		
		if(!isFileReady)
		{
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setLocalDateTime(LocalDateTime.now());
			exceptionResponse.setMessage("ERROR OCCURRED WHILE WRITING CSV");
			
			ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
		
		
		File csvFile = new File(csvFilePath+ "//csv_for_id_" + projectId + ".csv");
		InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(csvFile));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition",
				String.format("attachment; filename=\"%s\"", csvFile.getName()));
		httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		httpHeaders.add("Pragma", "no-cache");
		httpHeaders.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(httpHeaders)
				.contentLength(csvFile.length())
				.contentType(MediaType.parseMediaType("text/csv")).body(inputStreamResource);

		return responseEntity;
	}
	
	
	@GetMapping("/getcsvfile")
	public ResponseEntity<Object> getCSVContentForAllIds() throws FileNotFoundException, IOException
	{
		List<Project> listOfAllProjects = projectRepository.findAll();
		List<CSVFileContent> allCSVFileContentObjects = new ArrayList<CSVFileContent>();
		
		//First get all objects for all project ids
		for(Project proj : listOfAllProjects)
		{
			int projectId = proj.getProjectId();
			
			Address address = addressService.findAddressByProjectId(projectId);
			Project project = address.getProject();
			Schedule schedule = scheduleService.getByProjectId(projectId);
			UploadQuestions uploadQuestions = uploadQuestionsService.getByProjectId(projectId);
			FileEntity file = fileService.getByProjectId(projectId);
			InstallationDocument installationDocument = installationDocumentService.getByProjectId(projectId);
			Signature signature = signatureService.getByProjectId(projectId);
			
			
			CSVFileContent csvFileContent = new CSVFileContent();
			csvFileContent.setAddress(address);
			csvFileContent.setProject(project);
			csvFileContent.setSchedule(schedule);
			csvFileContent.setUploadQuestions(uploadQuestions);
			csvFileContent.setFile(file);
			csvFileContent.setInstallationDocument(installationDocument);
			csvFileContent.setSignature(signature);
			
			
			allCSVFileContentObjects.add(csvFileContent);
		}
		
		
		boolean isFileReady = writeCSVService.returnCSVForAllProjectIds(csvFilePath + "//csv_for_all_ids.csv", allCSVFileContentObjects);
		
		if(!isFileReady)
		{
			ExceptionResponse exceptionResponse = new ExceptionResponse();
			exceptionResponse.setLocalDateTime(LocalDateTime.now());
			exceptionResponse.setMessage("ERROR OCCURRED WHILE WRITING CSV");
			
			ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
		
		File csvFile = new File(csvFilePath+ "//csv_for_all_ids.csv");
		InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(csvFile));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition",
				String.format("attachment; filename=\"%s\"", csvFile.getName()));
		httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		httpHeaders.add("Pragma", "no-cache");
		httpHeaders.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(httpHeaders)
				.contentLength(csvFile.length())
				.contentType(MediaType.parseMediaType("text/csv")).body(inputStreamResource);

		return responseEntity;
		
		
	}
	
	//This method is just for testing the file download code
	@GetMapping("/download")
	public ResponseEntity<Object> downloadCSV() throws IOException
	{
		String fileName = "C:\\Users\\Shibra\\OneDrive\\Documents\\DWDM\\IRIS.csv";
		File file = new File(fileName);
		InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition",
				String.format("attachment; filename=\"%s\"", file.getName()));
		httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		httpHeaders.add("Pragma", "no-cache");
		httpHeaders.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(httpHeaders)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("text/csv")).body(inputStreamResource);

		return responseEntity;
	}
}
