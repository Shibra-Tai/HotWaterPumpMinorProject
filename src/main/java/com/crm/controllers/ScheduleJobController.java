package com.crm.controllers;
import com.crm.services.ScheduleService;
import com.crm.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.Project;
import com.crm.entities.Schedule;
import com.crm.entities.ScheduleJobRequest;
import com.crm.entities.User;

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
		Project p=new Project();
		p.setProjectId(scheduleJobRequest.getProjectId());
		schedule.setProject(p);
		schedule.setInstallerName(scheduleJobRequest.getUserNameOfInstaller());
		scheduleService.save(schedule);
		
		
		return new ResponseEntity<Schedule>(schedule,HttpStatus.OK);
		
	}
	
	@GetMapping("/getSchedule")
	public ResponseEntity<?> getAllSchedule()
	{	
		return new ResponseEntity<List<Schedule>>(scheduleService.getAll(),HttpStatus.OK);
	}

	@PostMapping("/getAllInstallers")
	public ResponseEntity<?> getAllInstallers()
	{
		List<User> listOfAllInstallers = userService.findAllInstallers();
		
		return new ResponseEntity<List<User>>(listOfAllInstallers,HttpStatus.OK);
	}
}
