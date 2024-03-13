package com.smartcontact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smartcontact.dao.UserRepository;
import com.smartcontact.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	//we will need useruserRepository object to extract user object from db.
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//first data(user) from database by the user name name which email id.
		//userRepository.getUserByUserName(user name); will return whole user object 
		//from the database 
		User user = userRepository.getUserByUserName(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("Could not find the user...");
		}
		
		
		//when we fetch the user object we will put the user object in CustomUserDetails
		//CustomUserDetails will fetch the password of the user which we will use to authenticate 
		//the user 
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails;
	}

}
