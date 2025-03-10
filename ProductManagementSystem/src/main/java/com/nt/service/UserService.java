package com.nt.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nt.entity.User;
import com.nt.enums.Roles;
import com.nt.exceptions.UserNotFoundException;
import com.nt.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private AuthenticationManager manager;
	
	public String register(User user){
		   if (user.getUserName().contains("@company.com")) {
	            user.setRoles(Set.of(Roles.Manager));  // Assign MANAGER
	        } else {
	            user.setRoles(Set.of(Roles.Customer)); // Default role CUSTOMER
	        }
		User user1 = repo.save(user);
	    if(user1!=null)
	    	return "Registerd Successfully "+user1;
	    else
	    	return "Failed To Register";
	}

	public String verify(User user) throws UserNotFoundException {
		Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));;
	    if(authentication.isAuthenticated())
	    	return "success";
	    else
	    	throw new UserNotFoundException("User Not Found");
	}
}
