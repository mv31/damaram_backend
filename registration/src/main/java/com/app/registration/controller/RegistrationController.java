package com.app.registration.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.registration.model.User;
import com.app.registration.service.RegistrationService;

@RestController
@CrossOrigin
public class RegistrationController {
	
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		if(tempEmail != null && !"".equals(tempEmail)) {
			User userobj = service.fetchUserByEmail(tempEmail);
			if(userobj != null) {
				throw new Exception("User with "+tempEmail+" is already exist");
			}
		}
		User userobj = null;
		userobj = service.saveUser(user);
		return userobj;
		
	}
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		String tempPass = user.getPassword();
		User userobj = null;
		if(tempEmail != null && tempPass !=null) {
			userobj = service.fetchUserByEmailAndPassword(tempEmail, tempPass);
		}
		if(userobj == null) {
			throw new Exception("Invalid Credentials");
		}
		return userobj;
	}
}
