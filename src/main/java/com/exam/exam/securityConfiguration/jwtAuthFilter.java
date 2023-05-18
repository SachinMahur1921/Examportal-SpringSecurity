package com.exam.exam.securityConfiguration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
 
 @Component
 @RequiredArgsConstructor

public class jwtAuthFilter extends OncePerRequestFilter {
       
     @Autowired
	  private jwtService jwtService;
	    
	    @Autowired
	   private  UserDetailsService  userDetailsService;
	  
	@Override
	protected void doFilterInternal(  HttpServletRequest request,  HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		final String authHeader= request.getHeader("authorization");
		 System.out.println(authHeader);
		 final String Jwt;
		 
		  final String usermail;
		   if(authHeader==null ||!authHeader.startsWith("Bearer "))
		   {   
			    System.out.println("i m here");
			   filterChain.doFilter(request, response);
			   return;
		   }
		      
		    Jwt=authHeader.substring(7);
		     usermail= jwtService.extractUsername(Jwt);
		     
		     if(usermail !=null && SecurityContextHolder.getContext().getAuthentication()==null)
		     {
		    	 UserDetails userDetails= userDetailsService.loadUserByUsername(usermail);
		    	 
		    	  if(jwtService.isTokenValid(Jwt, userDetails))
		    	  {    
		    		  UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
		    		   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    		    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		    		   
		    	  }
		     }
		     
		     filterChain.doFilter(request, response);
		
	}

}
