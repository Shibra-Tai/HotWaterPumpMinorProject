package com.crm.services;

import java.util.List;

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
	
	
	// This method returns null if no Schedule with demanded projectId is present
	public Schedule getByProjectId(int projectId)
	{
		List<Schedule> allSchedules = scheduleRepository.findAll();
		
		for(Schedule schedule : allSchedules) {
			if(schedule.getProjectId() == projectId)
			{
				return schedule;
			}
		}
		
		return null;
	}
	
	
	
	
}
