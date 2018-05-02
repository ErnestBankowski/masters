package com.stratum.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.stratum.model.User;

@Service
public class SessionService {
	
	@Autowired
	UserService userService;
	
	public Optional<User> findLoggedUser(OAuth2Authentication authentication) {
		String email = getSessionUserEmail(authentication);
		return userService.getOne(email);
	} 
	
	public void synchronizePrincipal(OAuth2Authentication authentication) {
		Optional<User> maybeUser = findLoggedUser(authentication);
		if(!maybeUser.isPresent()) {
			String email = getSessionUserEmail(authentication);
			User user = new User();
			user.setEmail(email);
			userService.save(user);
		}
	}
	
	public String getSessionUserEmail(OAuth2Authentication authentication) {
		Authentication userAuthentication = authentication.getUserAuthentication();
		Map details = (Map) userAuthentication.getDetails();
		return (String) details.get("email");
	}
	
}
