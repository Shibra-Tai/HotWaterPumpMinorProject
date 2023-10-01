package com.crm.controllers;
import com.crm.services.ScheduleService;
import com.crm.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.Schedule;
import com.crm.entities.ScheduleJobRequest;

@RestController
public class ScheduleJobController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	ScheduleService scheduleService;
	
	
	@PostMapping("/scheduleJob")
	public ResponseEntity<?> scheduleJob(@RequestBody ScheduleJobRequest scheduleJobRequest)
	{
		int userIdOfInstaller = userService.findIdByUserName(scheduleJobRequest.getUserNameOfInstaller());
		int userIdOfSalesman = userService.findIdByUserName(scheduleJobRequest.getUserNameOfSalesman());

		Schedule schedule = new Schedule();
		
		schedule.setUserIdOfInstaller(userIdOfInstaller);
		schedule.setUserIdOfSalesman(userIdOfSalesman);
		schedule.setScheduleDate(scheduleJobRequest.getScheduleDate());
		schedule.setProjectId(scheduleJobRequest.getProjectId());
		
		scheduleService.save(schedule);
		
		
		return new ResponseEntity<Schedule>(schedule,HttpStatus.OK);
		
		
	}
}
