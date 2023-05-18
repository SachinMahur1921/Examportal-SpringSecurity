package com.exam.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.exam.exam.model.Subjects;
 
@Repository
public interface subjectsRepository extends JpaRepository<Subjects,Long> {
  
	

}
