package com.exam.exam.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.naming.AuthenticationException;
import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam.model.Results;

import com.exam.exam.model.Subjects;
import com.exam.exam.model.User;
import com.exam.exam.repo.UserRepository;
import com.exam.exam.securityConfiguration.AuthentiationResponse;
import com.exam.exam.securityConfiguration.AuthenticateRequest;
import com.exam.exam.securityConfiguration.AuthenticateService;
import com.exam.exam.securityConfiguration.jwtService;
import com.exam.exam.services.impl.userServiceImpl;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
 
 
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@EnableMethodSecurity
public class UserController {
//	  @Autowired
//	 private UserService  userservice;
//	 
	  @Autowired
	  private userServiceImpl userServiceImpl;
	   
	   @Autowired
	  private AuthenticateService authenticateService;
	  
	   @Autowired
	 private  UserRepository userRepository;
	  
	   
	    @Autowired
       private jwtService jwtService;
		
	    
     @GetMapping("/{username}")
		
		public User getUser(@PathVariable ("username") String username)
		{
			return this.userServiceImpl.getUser(username);
			
		}
           
       
          
            
        	
        	@GetMapping ("/validate/{username}/{password}")
            public boolean getCurrentUser(@PathVariable("username")String username,@PathVariable("password")String password )
            {     
        		
        		boolean flag=false;
        		System.out.println(username+"inside current"+ password);
        		 String username1=this.userServiceImpl.getUser(username).getUsername();
        		 String password1=this.userServiceImpl.getUser(username).getPassword();
        		  System.out.println();
        		  
        	     if(username1.equals(username) && password.equals(password1) )
        	     {
        	    	 flag=true;
        	    	 
        	     }
        	    return flag;
         
            }
        	  
        	 @PreAuthorize("hasAuthority('ADMIN')")
        	 @GetMapping("/getStudent")
        	 
        	  public List<User> getdetails(Authentication authentication)
        	  { 
				return  this.userServiceImpl.getdetails();
        		 
        	  } 
        	  
        	 @PreAuthorize("hasAuthority('ADMIN')")
        	 @GetMapping("/deleteuser/{id}")
        	  public void  delete( @PathVariable("id") String id)
        	  {
        		  
        		 long newid= (long) Integer.parseInt(id);
        		  userServiceImpl.deleteUser(newid);
        		 
        	  }
        	 @PreAuthorize("hasAuthority('ADMIN')")
        	 @PostMapping("/addSubjects")
        	 public void addSubjects( @RequestBody Map<String, String> requestMap)
        	  {
        		 userServiceImpl.addSubjects(requestMap);
        		 
        	  } 
        	 
        	  @PreAuthorize("hasAuthority('ADMIN')")
        	  @GetMapping("/getSubjects")
        	  
        	   public  List<Subjects> getSubjects()
        	   
        	   {
        		 return  userServiceImpl.getsubjects();
        	   }
        	  @PreAuthorize("hasAuthority('ADMIN')")
        	   @GetMapping("/deletesubject/{id}")
        	      public void  deleteSubject( @PathVariable("id") String id)
         	  {
         		  
         		 long newid= (long) Integer.parseInt(id);
         	     userServiceImpl.deleteSubjects(newid);
         		 
         	  }
        	   
//          	   @PostMapping("/addMarks")
//          	 
//         	  public void addMarks(@RequestBody Map<String, String> requestMap )
//         	  {
//         		  
//          		   String username=requestMap.get("username");
//          		   int Score= Integer.parseInt(requestMap.get("Score"));
//          		    userServiceImpl.addMarks(username,Score);
//         		  
//         	  }
	  
        	    @GetMapping("/hello") 
        	   public String hello()
        	   {     
        	    	
        	    	 System.out.println("hello world");
        	    	 return "Success";
        	   }
           
        	      
        	     @PostMapping("/register")
        	     public ResponseEntity<AuthentiationResponse>register(@RequestBody Map<String, String> requestmap)
        	     {  
        	    	 System.out.println(requestmap);
					return ResponseEntity.ok(authenticateService.register(requestmap));
        	    	 
        	     } 
        	         
        	      
        	      @PostMapping("/authenticate")
        	      
        	       public ResponseEntity<AuthentiationResponse> authenticate(@RequestBody Map<String, String> requestmap) throws AuthenticationException
        	       { 
        	    	   
        	    	  return ResponseEntity.ok(authenticateService.authenticate(requestmap));
        	    	  
        	       }
        	      @PreAuthorize("hasAuthority('USER')")
        	      @GetMapping("/demo")
        	      public ResponseEntity<String> demo()
        	      {  
        	    	   
        	    	  System.out.println("i m user");
        	    	  return ResponseEntity.ok(" i m user");
        	      }
        	    
        	      @PreAuthorize("hasAuthority('ADMIN')")
        	      @GetMapping("/dem")
        	      public ResponseEntity<String> dem()
        	      {
        	    	  return ResponseEntity.ok(" i m Admin");
        	      }
        	      
        	     
        	      @GetMapping("/token")
        	      public ResponseEntity<String> getUserByToken(Authentication authentication) {
        	    	  System.out.println("kjs");
        	    	  String username = authentication.getName();
        	    	   System.out.println(username);
        	    	 
        	    	   Optional<User> user=  userRepository.findByEmail(username);
        	             
        	    	     System.out.println("getting role done");
        	    	      System.out.println(user.get().getRole());
        	    	     
        	              return ResponseEntity.ok( user.get().getRole());
        	      }
        	      
        	      @PreAuthorize("hasAuthority('ADMIN')")
        	     @GetMapping("/getResults")
        	      public List<Results> getresult()
        	      {
					return  userServiceImpl.getresults();
        	      }
        	      @PreAuthorize("hasAuthority('USER')")
        	     @PostMapping("/addResults")
        	     public void addresults(@RequestBody String score,Authentication authentication)
        	     {  
        	    	  System.out.println("username");
        	    	  String username= authentication.getName();
        	    	  System.out.println(username);
        	    	  System.out.println(score);
        	    	  userServiceImpl.addResults(score,username);
        	    	 
        	     }
        	  
            
}
