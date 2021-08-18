package com.pensionerDetailsMicroservice.Controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pensionerDetailsMicroservice.Model.CustomErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AadharNumberNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleAadharNumberNotFoundException(AadharNumberNotFoundException ex){
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
		customResponse.setMessage(ex.getMessage());
		customResponse.setReason("Invalid Aadhar provided");
		customResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> handleException(Exception ex){
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
		customResponse.setMessage(ex.getMessage());
		customResponse.setReason("Invalid Request Information");
		customResponse.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.BAD_REQUEST);
	}
	
}
