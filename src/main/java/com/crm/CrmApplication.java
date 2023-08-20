package com.crm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CrmApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(CrmApplication.class, args);
		System.out.println("Hello, Application is up and running");
	}

}
