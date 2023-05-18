package com.exam.exam.securityConfiguration;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.exam.exam.model.User;
import com.exam.exam.repo.UserRepository;


import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
  
	
	 
	 @Autowired
	 AuthenticationManager authenticationManager;
	 
	  @Autowired
	 private UserRepository userRepository;
	  
	 @Autowired
	 private jwtService jwtService;
	 
	  @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 public AuthentiationResponse register(Map<String, String> requestMap)
	 { 
		 
		 User user= new User();
		 user.setFirstname(requestMap.get("firstname"));
		 user.setLastname(requestMap.get("Lastname"));
		 user.setPassword(passwordEncoder.encode(requestMap.get("password")));
		 user.setEmail( requestMap.get("useremail"));
		 user.setUsername(requestMap.get("username"));
//		 user.setRole(RoleDup.USER);
		 String roleString = requestMap.get("role");
//		  
		    user.setRole("USER");
		    
		    userRepository.save(user);
	
		 
		String jwtToken=jwtService.genratetoken(user);
		return AuthentiationResponse.builder().token(jwtToken).build();
		  
		 
	 }
	 
	public AuthentiationResponse authenticate(Map<String, String> requestmap)
	{
		 System.out.println("helo worlsl");
			System.out.println(requestmap);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestmap.get("useremail"), requestmap.get("password")));
		System.out.println( "helloworld");
		System.out.println(requestmap);
		Optional<User> userOptional = userRepository.findByEmail(requestmap.get("useremail"));

				User user = userOptional.orElseThrow(() -> new NoSuchElementException("User not found"));
		
		System.out.println( "helloworld");
		String jwtToken= jwtService.genratetoken(user);
		return  AuthentiationResponse.builder().token(jwtToken).build();
		
	}

	public Optional<User> findByToken(String token) {
		
		String username=jwtService.extractUsername(token);
		
		  Optional<User> user= userRepository.findByEmail(username);
		// TODO Auto-generated method stub
		 return user;
	}

//	public Optional<User> findByToken(String token) {
//		
//		Optional<User> user= userRepository.findByToken(token);
//		
//		return user;
//	}
}
	              
