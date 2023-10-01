package com.crm.services;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crm.entities.Schedule;
import com.crm.entities.ScheduleEmailRequest;
import com.crm.repositories.ScheduleRepository;

@Service
public class EmailSchedulerService 
{
	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired 
	UserService userService;
	
//	@Scheduled(fixedDelay=1000*60*60*24) // This method runs every 24hrs
	public void iterateJobsFromDatabase()
	{
		List<Schedule> listOfAllSchedules = scheduleRepository.findAll();
		
		for(Schedule  s : listOfAllSchedules)
		{
			int userIdOfInstaller = s.getUserIdOfInstaller();
			String mailIdOfInstaller = userService.findUserEmailByUserId(userIdOfInstaller);
			ScheduleEmailRequest scheduleEmailRequest=new ScheduleEmailRequest();
			
			Date today = new Date(0);
			
			if(today.compareTo(s.getScheduleDate()) == 0)
			{
				//Converting date to LocalDateTime
				//1000*60*60*9 --> mail will be sent at 9:00 AM 
				LocalDateTime localDateTime = 
						Instant.ofEpochMilli(s.getScheduleDate()
						.getTime() + 1000*60*60*9).atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				
				System.out.println("Local date time : "+localDateTime);
										
				scheduleEmailRequest.setDateTime(localDateTime);
				scheduleEmailRequest.setEmail(mailIdOfInstaller);
				
				scheduleJobs(scheduleEmailRequest);
				System.out.println("DONE....");
			}
			
			
		}
		
		
	}
	
	
	public void scheduleJobs(ScheduleEmailRequest scheduleEmailRequest)
	{
		try 
        {
            ZonedDateTime dateTime = ZonedDateTime.of(scheduleEmailRequest.getDateTime(), scheduleEmailRequest.getTimeZone());
            
            if(dateTime.isBefore(ZonedDateTime.now())) 
            {                
                System.out.println("Time not specified properly, scheduling failed");
                
                //return ResponseEntity.badRequest().body(scheduleEmailResponse);
            }
            
            JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);
       
            System.out.println("EMAIL SCHEDULED");
            //return new ResponseEntity<String>("EMAIL SCHEDULED",HttpStatus.OK);

        } 
        catch (SchedulerException ex) 
        {           
            System.out.println("ERROR OCCURRED WHILE SCHEDULING");
        }
	}
	
	
	private JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest) 
	{
		
        JobDataMap jobDataMap = new JobDataMap();
        // We have set parameter, and this will pass the info to the job
        jobDataMap.put("email", scheduleEmailRequest.getEmail());
        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
        jobDataMap.put("body", scheduleEmailRequest.getBody());
        
        
        
        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
    
	private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) 
	{
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
