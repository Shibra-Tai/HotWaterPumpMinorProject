package com.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.Schedule;
import com.crm.repositories.ScheduleRepository;

@Service
public class ScheduleService 
{
	@Autowired
	ScheduleRepository scheduleRepository;
	
	public void save(Schedule schedule)
	{
		scheduleRepository.save(schedule);
		
	}
	
	
	
}
