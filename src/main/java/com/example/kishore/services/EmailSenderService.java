package com.example.kishore.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender; 
	
	private String otp="";
	public void sendOtpt(String email,String username)
	{
		 Random rnd = new Random();
	        int number = rnd.nextInt(999999);

	        // this will convert any number sequence into 6 character.
	         otp = String.format("%06d", number);
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		
		
		message.setTo(email);

        String body="Hello "+username+" !!\n\n"+
                "You have requested to reset your password\n"+
                "Otp for Resetting the password "+otp+"\n\n"+
                "Ignore this email if you do remember your password or  you have not made the request.";


        String subject="Reset Password request";

        message.setText(body);
        message.setSubject(subject);
		
		javaMailSender.send(message);
		
	}
	public boolean verifyOtp(String num)
	{
		System.out.println(otp+"-"+num);
		if(otp.equals(num))
		{
			return true;
		}
		return false;
	}
}
