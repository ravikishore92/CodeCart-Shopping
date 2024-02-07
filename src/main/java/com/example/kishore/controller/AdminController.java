package com.example.kishore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kishore.entity.Admin;
import com.example.kishore.repository.AdminRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class AdminController {

     @Autowired
	private AdminRepository adminRepo;
     
    
    
    @GetMapping("/adminlogin/{username}")
    public boolean adminLogin(@PathVariable("username") String username)
    {
    	Admin admin = adminRepo.findByUsername(username);
    	System.out.println(admin);
    	
    	if(admin == null)
    	{
    		return false;
    	}
    	
    	return true;
    	
    	
    }
    	
}
