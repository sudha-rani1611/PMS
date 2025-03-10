package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.User;
import com.nt.exceptions.UserNotFoundException;
import com.nt.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) throws Exception
	{
		encoder.encode(user.getPassword());
		String s = service.register(user);
		if(s!=null)
		return new ResponseEntity<String>(s,HttpStatus.OK);
		else
			return new ResponseEntity<String>(s,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) throws UserNotFoundException
	{
		return service.verify(user);
	}
}
