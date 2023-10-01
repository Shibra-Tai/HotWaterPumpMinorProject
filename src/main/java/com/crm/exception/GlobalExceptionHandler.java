package com.crm.exception;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crm.exception.ExceptionResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException unfe)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setLocalDateTime(LocalDateTime.now());
		exceptionResponse.setMessage("USER_NOT_FOUND");
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException nsee)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setLocalDateTime(LocalDateTime.now());
		exceptionResponse.setMessage("DOES_NOT_EXISTS_IN_DB");
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException iae)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setLocalDateTime(LocalDateTime.now());
		exceptionResponse.setMessage("ILLEGAL_ARGUMENT");
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException eje)
	{
		System.out.println("-----------------Global exp handler---------------------");
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setLocalDateTime(LocalDateTime.now());
		exceptionResponse.setMessage("TOKEN_EXPIRED");
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@ExceptionHandler(value = MalformedJwtException.class)
	public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException mje)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setLocalDateTime(LocalDateTime.now());
		exceptionResponse.setMessage("MALFORMED_TOKEN");
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@ExceptionHandler(value = SchedulerException.class)
	public ResponseEntity<Object> handleSchedulerException(SchedulerException se)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setLocalDateTime(LocalDateTime.now());
		exceptionResponse.setMessage("ERROR_SCHEDULING");
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleOtherException(Exception e)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setLocalDateTime(LocalDateTime.now());
		exceptionResponse.setMessage("EXCEPTION: "+e.getMessage());
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
	
	
	
}
