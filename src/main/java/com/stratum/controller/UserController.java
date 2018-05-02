package com.stratum.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stratum.model.Project;
import com.stratum.model.User;
import com.stratum.service.SessionService;
import com.stratum.service.UserService;

@RestController
@RequestMapping("/users/")
@CrossOrigin(origins = "http://localhost:4200")
@EnableOAuth2Sso
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public void authenticateUser(OAuth2Authentication principal) {
		sessionService.synchronizePrincipal(principal);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<User> listUsers() {
		return userService.list();
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable(value="email") String email) {
        Optional<User> maybeUser = userService.getOne(email);
        if (maybeUser.isPresent()) {
        	return new ResponseEntity<User>(maybeUser.get(), HttpStatus.OK);         
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	
	
}
