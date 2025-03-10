package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.classes.CustomUserDetails;
import com.nt.entity.User;
import com.nt.exceptions.UserNotFoundException;
import com.nt.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public CustomUserDetails loadUserByUsername(String username){
		User user = repo.findByUserName(username);
		if(user==null)
		{
			try
			{
				throw new UserNotFoundException("User Not Found");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return new CustomUserDetails(user);
	}

}
