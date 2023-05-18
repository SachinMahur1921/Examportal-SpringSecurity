package com.exam.exam.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.exam.exam.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
public User findByUsername(String username);
 
//@Query("update User set marks=:marks where username=:username")
//public void update(  @Param("username") String username,@Param("marks") int marks );
//   
  public   Optional<User> findByEmail(String email); 
 
    
   
}