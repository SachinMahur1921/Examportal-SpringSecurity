package com.exam.exam.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.exam.exam.repo.UserRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
     
	 @Autowired
	private  UserRepository repository ;
	  
	
	  @Bean
	   public UserDetailsService userDetailsService()
	   {
		  return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("usernot found"));
			}
		};
	   }
	
	
	 @Bean
	   public AuthenticationProvider authenticationProvider()
	   {
		 
		  DaoAuthenticationProvider  authProvider= new DaoAuthenticationProvider();
		     
		   System.out.println("detailsevice");
		   authProvider.setUserDetailsService(userDetailsService());
		   System.out.println(authProvider);
		   authProvider.setPasswordEncoder(passwordEncoder());
		  
		return authProvider;
		 
	   }
	 
	  @Bean
	  
	  PasswordEncoder passwordEncoder()
	  {
		   return new BCryptPasswordEncoder();
	  }
	 
	   @Bean 
	   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	   {
		   
		   
		return config.getAuthenticationManager();
		   
	   }
	
}
