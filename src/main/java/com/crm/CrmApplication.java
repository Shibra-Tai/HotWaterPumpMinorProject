package com.crm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
//@EnableScheduling // For email scheduling
public class CrmApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(CrmApplication.class, args);
		System.out.println("Hello, Application is up and running");
	}

}
