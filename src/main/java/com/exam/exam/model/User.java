package com.exam.exam.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor

 
public class User implements UserDetails  {
    
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="userId",nullable=false)
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String profile;
	private String  password;
	private Integer  Marks;
	
	
	private String role;
	
	
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getMarks() {
		return Marks;
	}

	public void setMarks(Integer marks) {
		Marks = marks;
	}

	public User(String password) {
		
		this.password = password;
	}
  

	public void setPassword(String password) {
		this.password = password;
	}

	
	

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER,mappedBy ="user" )
	@JsonIgnore
	
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	private boolean enabled=true;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
   

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User(Long id, String username, String firstname, String lastname, String email, String phone, String profile,
			boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.profile = profile;
		this.enabled = enabled;
		
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		  return Arrays.stream(this.role.split(",")).map(SimpleGrantedAuthority::new)
				  	.collect(Collectors.toList());
		 
		
		
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return  true;
	} 


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	 @Override
		public String getUsername() {
			return email;
		}

     @Override
public boolean isEnabled() {
	return true;
}

	  @Override
	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
 
	 
	
	

	
	

	

	
} 
