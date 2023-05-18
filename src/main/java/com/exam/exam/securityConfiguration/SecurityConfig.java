package com.exam.exam.securityConfiguration;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;





@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    

	
	
	
	    @Autowired
		private jwtAuthFilter jwtAuthFilter;
	    
	      
	       @Autowired
		private AuthenticationProvider authenticationprovider;
	    
	    @Bean
	    public AuthenticationManager authManager( AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        //AuthenticationManager authManager = new ProviderManager(new MyFirstCustomAuthenticationProvider());
	       
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	    
	
	@Bean
   public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
   {
	
	 httpSecurity.csrf()
	 .disable()
	 .authorizeHttpRequests().
	  requestMatchers("/user/register").permitAll().
	  requestMatchers("/user/authenticate").permitAll().
	  anyRequest()
	 .authenticated()
	 .and()
	 . sessionManagement()
	 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	 .and().authenticationProvider(authenticationprovider)
	 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	 .cors();
	  return httpSecurity.build();
   } 
	 
	
	
	
}
