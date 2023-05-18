package com.exam.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.model.Results;

public interface  ResultsRepository  extends JpaRepository<Results, Long>{
    
	 
}
