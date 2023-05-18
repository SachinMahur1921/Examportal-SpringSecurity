package com.exam.exam.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import java.lang.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exam.model.Results;
import com.exam.exam.model.Subjects;
import com.exam.exam.model.User;

import com.exam.exam.repo.subjectsRepository;
import com.exam.exam.repo.ResultsRepository;
import com.exam.exam.repo.UserRepository;


 @Service
public class userServiceImpl{
   @Autowired
	private UserRepository userRepository;
   
 
    
     @Autowired
    private subjectsRepository subjectsRepository;
    
	 @Autowired
	 private ResultsRepository repository;
	
	//getting user by username
	
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}
	
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
//		Optional<User> findById = this.userRepository.findById(userId);
//		User user1= findById.get();
//		user1.setEmail("rajat@gmail.com");
//		this.userRepository.saveAndFlush(user1);
		this.userRepository.deleteById(userId);
	}



	public List<User> getdetails() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	public void addSubjects(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		 
		Subjects subject = new Subjects();
		
		 subject.setSubjectName(requestMap.get("subjectName"));
		 subject.setTotalQuestions(25);
		 subject.setTotalMarks(25*4);
		this.subjectsRepository.save(subject);
	}

	public List<Subjects> getsubjects() {
		// TODO Auto-generated method stub
	 return	this.subjectsRepository.findAll();
		
	}
        
	public void deleteSubjects(long newid) {
		 
		this.subjectsRepository.deleteById(newid);
	}

//	public void addMarks(String username,int score) {
//		
//		 int marks1=score;
//		 
//		 this.userRepository.update( "sachin",marks1);
//		
//	 
//	}
	
	  public List<Results> getresults(){
		  
		  return repository.findAll();
	  }

	public void addResults(String score, String username) {
		// TODO Auto-generated method stub 
		
		Optional<User> user = userRepository.findByEmail(username);
		  
		   if(username.equals(user.get().getEmail()))
		   {
			    return ;
		   }
		 Results results = new Results();
		  results.setEmail(user.get().getEmail());
		   results.setUsername(user.get().getUsername());
		   results.setTotalQuestions(25);
		   results.setMarks(4* Integer.parseInt(score));
		    repository.save(results);
		
	}
	  
 
}

 