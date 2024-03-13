package com.smartcontact.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smartcontact.entities.User;

public interface UserRepository  extends JpaRepository<User, Integer >{
	
//@Param is used to denote that the method parameter 
//should be binded to the query named parameter :email
	//THis method is used to extract the password of the user name provided in the login
	//and check if the password is correct or not 
	@Query("select u from User u where u.email= :email")
	public User getUserByUserName(@Param("email") String email);

}
