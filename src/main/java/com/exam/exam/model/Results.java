package com.exam.exam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
 @Table(name="Results")
public class Results {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     private Long Id;
	 public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	private String username;
	  private String email;
	 public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private Integer TotalQuestions;
	 private Integer Correct;
	 private Integer  Marks;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getTotalQuestions() {
		return TotalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		TotalQuestions = totalQuestions;
	}
	public Integer getCorrect() {
		return Correct;
	}
	public void setCorrect(Integer correct) {
		Correct = correct;
	}
	public Integer getMarks() {
		return Marks;
	}
	public void setMarks(Integer marks) {
		Marks = marks;
	}
	public Results(String username, Integer totalQuestions, Integer correct, Integer marks) {
		super();
		this.username = username;
		TotalQuestions = totalQuestions;
		Correct = correct;
		Marks = marks;
	}
	public Results() {
		
	}
 	
	 
}
