


package com.example.kishore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.kishore.entity.Admin;
import com.example.kishore.repository.AdminRepository;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext c =  SpringApplication.run(ShoppingApplication.class, args);
		AdminRepository adminRepo = c.getBean(AdminRepository.class);
		
		
	}
	public static void addAdmin(AdminRepository adminRepo)
	{
		
	        Admin admin = new Admin(1l,"ravi","ravi");
	        
	        adminRepo.save(admin);
	}


}
