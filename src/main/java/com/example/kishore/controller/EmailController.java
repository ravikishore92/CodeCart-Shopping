package com.example.kishore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kishore.entity.Customer;
import com.example.kishore.repository.CustomerRepository;
import com.example.kishore.services.EmailSenderService;

@CrossOrigin("*")
@RestController
public class EmailController {

	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/forgotpass/{email}")
	public ResponseEntity<?> sendOtp(@PathVariable("email") String email)
	{
		
	    	Customer customer = customerRepository.findByEmail(email);
	    	
	    	if(customer == null)
	    	{
	    		System.out.println("in null");
	    		return ResponseEntity.badRequest().body("Email is Not Found");
	    	}
	    	
	    	emailSenderService.sendOtpt(email,customer.getUsername());
	    	
	    	
	    	return ResponseEntity.ok().body("otp sended");
	}
	
	@GetMapping("/forgotpass/verifyotp/{otp}")
	public ResponseEntity<?> verifyOtp(@PathVariable("otp") String otp)
	{
		
		if(emailSenderService.verifyOtp(otp))
		{
			
			return ResponseEntity.ok().body("Verified");
		}
		
		
		return ResponseEntity.badRequest().body("invalid otp");
	}
}
