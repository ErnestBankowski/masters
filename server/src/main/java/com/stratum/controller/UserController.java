package com.stratum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stratum.model.User;
import com.stratum.service.UserService;

@RestController
@RequestMapping("/users/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<User> listUsers() {
		return userService.listUsers();
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable(value="id") String id) {
		return userService.getUserById(Long.parseLong(id));
	}
	
}
