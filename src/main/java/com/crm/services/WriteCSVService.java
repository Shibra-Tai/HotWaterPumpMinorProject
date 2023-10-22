package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.CSVFileContent;
import com.crm.entities.Project;
import com.crm.repositories.ProjectRepository;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
@Service
public class WriteCSVService 
{
	@Autowired
	ProjectRepository projectRepository;
	
	File file = null;
	
	
	public boolean returnCSVForGivenProjectId(String fileName, CSVFileContent csvFileContent) 
	{
			
        try 
        {
        	file = new File(fileName);
        	FileWriter fileWriter = new FileWriter(file);
        	CSVWriter csvWriter = new CSVWriter(fileWriter);
        	
        	String[] headerOfCSV = {
        							"projectId","projectTitle","customerFirstName","customerLastName","customerPhoneNumber","customerMobileNumber","customerType","companyName","abnNumber","gstRegistered","emailId",
        							"addressId","buildingName","unitNumber","lotNumber","streetNumber","streetName","streetTypeSuffix","suburb","state","postcode",	
        							"scheduleId","userIdOfInstaller","userIdOfSalesman",
        							"questionId","signedNominationFormPast","buildingType","occupyByBusiness","titleOfCustomer","fullNameOfCustomer","quotingFor","typeOldSystem","replaceAirSource","solarWaterHeater","gasReplaceAirSource","gasSolarWaterHeaterElectricBoosted","gasSolarWaterHeaterGasBoosted","paid",
        							"fid - File id",
        							"iid - Installation document id",
        							"signId - Signature id"
        						};
        	csvWriter.writeNext(headerOfCSV);
        	
        	String[] rowOfCSV = {
        			""+csvFileContent.getAddress().getProject().getProjectId(), csvFileContent.getProject().getProjectTitle(), csvFileContent.getProject().getCustomerFirstName(), csvFileContent.getProject().getCustomerLastName(), ""+csvFileContent.getProject().getCustomerPhoneNumber(), ""+csvFileContent.getProject().getCustomerMobileNumber(), ""+csvFileContent.getProject().getCustomerType(), csvFileContent.getProject().getCompanyName(), ""+csvFileContent.getProject().getAbnNumber(), csvFileContent.getProject().getGstRegistered(), csvFileContent.getProject().getEmailId(),
        			
        			""+csvFileContent.getAddress().getAddressId(), csvFileContent.getAddress().getBuildingName(), csvFileContent.getAddress().getUnitNumber(), csvFileContent.getAddress().getLotNumber(), csvFileContent.getAddress().getStreetNumber(), csvFileContent.getAddress().getStreetName(), csvFileContent.getAddress().getStreetTypeSuffix(), csvFileContent.getAddress().getSuburb(), csvFileContent.getAddress().getState(), csvFileContent.getAddress().getPostcode(),
        			
        			""+csvFileContent.getSchedule().getScheduleId(), ""+csvFileContent.getSchedule().getUserIdOfInstaller(), ""+csvFileContent.getSchedule().getUserIdOfSalesman(),
        			
        			""+csvFileContent.getUploadQuestions().getQuestionId(), csvFileContent.getUploadQuestions().getSignedNominationFormPast(), csvFileContent.getUploadQuestions().getBuildingType(), csvFileContent.getUploadQuestions().getOccupyByBusiness(), csvFileContent.getUploadQuestions().getTitleOfCustomer(), csvFileContent.getUploadQuestions().getFullNameOfCustomer(),  csvFileContent.getUploadQuestions().getQuotingFor(),  csvFileContent.getUploadQuestions().getTypeOldSystem(),  csvFileContent.getUploadQuestions().getReplaceAirSource(), csvFileContent.getUploadQuestions().getSolarWaterHeater(), csvFileContent.getUploadQuestions().getGasReplaceAirSource(), csvFileContent.getUploadQuestions().getGasSolarWaterHeaterElectricBoosted(), csvFileContent.getUploadQuestions().getGasSolarWaterHeaterGasBoosted(), csvFileContent.getUploadQuestions().getPaid(),
        			
        			""+csvFileContent.getFile().getFid(),
        			
        			""+csvFileContent.getInstallationDocument().getIid(),
        			
        			""+csvFileContent.getSignature().getSignId()
        	};
            
        	csvWriter.writeNext(rowOfCSV);
        	
        	csvWriter.close();
        	fileWriter.close();
        	
        	
        } 
        catch (IOException e) 
        {
            System.out.println("IO EXCEPTION IN WriteCSVService");
            return false;
        }
        catch (Exception e) 
        {
            System.out.println("EXCEPTION IN WriteCSVService");
            return false;
        }
        
        return true;
    }

	
	public boolean returnCSVForAllProjectIds(String fileName, List<CSVFileContent> allCSVFileContentObjects) 
	{
		
		try
		{
			file = new File(fileName);
        	FileWriter fileWriter = new FileWriter(file);
        	CSVWriter csvWriter = new CSVWriter(fileWriter);
        	
        	String[] headerOfCSV = {
        							"projectId","projectTitle","customerFirstName","customerLastName","customerPhoneNumber","customerMobileNumber","customerType","companyName","abnNumber","gstRegistered","emailId",
        							"addressId","buildingName","unitNumber","lotNumber","streetNumber","streetName","streetTypeSuffix","suburb","state","postcode",	
        							"scheduleId","userIdOfInstaller","userIdOfSalesman",
        							"questionId","signedNominationFormPast","buildingType","occupyByBusiness","titleOfCustomer","fullNameOfCustomer","quotingFor","typeOldSystem","replaceAirSource","solarWaterHeater","gasReplaceAirSource","gasSolarWaterHeaterElectricBoosted","gasSolarWaterHeaterGasBoosted","paid",
        							"fid - File id",
        							"iid - Installation document id",
        							"signId - Signature id"
        						};
        	csvWriter.writeNext(headerOfCSV);
		
			for(CSVFileContent csvFileContent : allCSVFileContentObjects)
			{
				String[] rowOfCSV = {
	        			""+csvFileContent.getAddress().getProject().getProjectId(), csvFileContent.getProject().getProjectTitle(), csvFileContent.getProject().getCustomerFirstName(), csvFileContent.getProject().getCustomerLastName(), ""+csvFileContent.getProject().getCustomerPhoneNumber(), ""+csvFileContent.getProject().getCustomerMobileNumber(), ""+csvFileContent.getProject().getCustomerType(), csvFileContent.getProject().getCompanyName(), ""+csvFileContent.getProject().getAbnNumber(), csvFileContent.getProject().getGstRegistered(), csvFileContent.getProject().getEmailId(),
	        			
	        			""+csvFileContent.getAddress().getAddressId(), csvFileContent.getAddress().getBuildingName(), csvFileContent.getAddress().getUnitNumber(), csvFileContent.getAddress().getLotNumber(), csvFileContent.getAddress().getStreetNumber(), csvFileContent.getAddress().getStreetName(), csvFileContent.getAddress().getStreetTypeSuffix(), csvFileContent.getAddress().getSuburb(), csvFileContent.getAddress().getState(), csvFileContent.getAddress().getPostcode(),
	        			
	        			""+csvFileContent.getSchedule().getScheduleId(), ""+csvFileContent.getSchedule().getUserIdOfInstaller(), ""+csvFileContent.getSchedule().getUserIdOfSalesman(),
	        			
	        			""+csvFileContent.getUploadQuestions().getQuestionId(), csvFileContent.getUploadQuestions().getSignedNominationFormPast(), csvFileContent.getUploadQuestions().getBuildingType(), csvFileContent.getUploadQuestions().getOccupyByBusiness(), csvFileContent.getUploadQuestions().getTitleOfCustomer(), csvFileContent.getUploadQuestions().getFullNameOfCustomer(),  csvFileContent.getUploadQuestions().getQuotingFor(),  csvFileContent.getUploadQuestions().getTypeOldSystem(),  csvFileContent.getUploadQuestions().getReplaceAirSource(), csvFileContent.getUploadQuestions().getSolarWaterHeater(), csvFileContent.getUploadQuestions().getGasReplaceAirSource(), csvFileContent.getUploadQuestions().getGasSolarWaterHeaterElectricBoosted(), csvFileContent.getUploadQuestions().getGasSolarWaterHeaterGasBoosted(), csvFileContent.getUploadQuestions().getPaid(),
	        			
	        			""+csvFileContent.getFile().getFid(),
	        			
	        			""+csvFileContent.getInstallationDocument().getIid(),
	        			
	        			""+csvFileContent.getSignature().getSignId()
	        	};
	            
	        	csvWriter.writeNext(rowOfCSV);
	        		
			}
			
			csvWriter.close();
        	fileWriter.close();
		}
		catch (IOException e) 
        {
            System.out.println("IO EXCEPTION IN WriteCSVService - returnCSVForAllProjectIds method");
            return false;
        }
        catch (Exception e) 
        {
            System.out.println("EXCEPTION IN WriteCSVService - returnCSVForAllProjectIds method");
            return false;
        }
		
        return true;
    }
	
	
}
