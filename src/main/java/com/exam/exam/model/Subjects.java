package com.exam.exam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
 @Entity
 @ Table(name="subjects")
public class Subjects {
       
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	
	 private Long Id;
	 public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	private String subjectName;
	 private  Integer totalQuestions;
	 private  Integer totalMarks;
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public Integer getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	 
	 
}
