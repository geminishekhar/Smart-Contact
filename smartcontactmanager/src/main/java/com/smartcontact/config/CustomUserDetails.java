package com.smartcontact.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartcontact.entities.User;

public class CustomUserDetails implements UserDetails {
	
	//Input will be user object and from that user object we will extract the user name and password.
	private User user;
	
	//constructor to initialize the user .
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	//This method will simply return all the roles of the users .
	//Since the return type is collection so we are keeping all the roles in list
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		//since for me the user name is user email
		//while logging in the user will input the user email id as user name .
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		//keeping this true as of now as we don't use it now
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//keeping this true as of now as we don't use it now
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//keeping this true as of now as we don't use it now
		return true;
	}

	@Override
	public boolean isEnabled() {
		//keeping this true as of now as we don't use it now
		return true;
	}

}
