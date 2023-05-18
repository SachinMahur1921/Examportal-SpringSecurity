package com.exam.exam;

import java.sql.Driver;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.exam.exam.model.User;

import com.exam.exam.services.impl.userServiceImpl;

@SpringBootApplication
public class ExamserverApplication {
	  
	  

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
		 
	
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//	 	System.out.println("starting code");
////		
////		  User user=new User();
////		  user.setFirstname("Sachin");
////		  user.setLastname("Tiwari");
////		  user.setUsername("DGGupta");
////		  user.setPassword("abc");
////          user.setEmail("abc@gmail.com");
////          user.setProfile("default.png");
////          user.setPhone("6397170796");
////         
////           Role role1=new Role();
////           role1.setRoleId(44L);
////           role1.setRoleName("Admin");
////           
////           Set<userRole> userRoleSet=new HashSet<>();
////            userRole userRole=new userRole();
////            userRole.setRole(role1);
////            userRole.setUser(user);
////           userRoleSet.add(userRole);
////          User user1= this.userservice.createUser(user, userRoleSet);
////           System.out.println(user1.getUsername());
	
	    
//		
//	}

}
