package com.stratum.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.stratum.messaging.MailClient;
import com.stratum.model.Functionality;
import com.stratum.model.Specification;
import com.stratum.model.User;
import com.stratum.service.FunctionalityService;
import com.stratum.service.SessionService;
import com.stratum.service.SpecificationService;

@RestController
@RequestMapping("/specification")
@CrossOrigin(origins = "http://localhost:4200")
public class SpecificationController {

	@Autowired
	SpecificationService specificationService;
	
	@Autowired
	FunctionalityService functionalityService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired 
	MailClient mailClient;
	
	@RequestMapping(value = "/for/{id}", method = RequestMethod.GET)
	public ResponseEntity<Specification> getAllForFunctionality(@PathVariable("id") long id) {
		Specification forFunctionality = specificationService.getForFunctionality(id);
		return new ResponseEntity<Specification>(forFunctionality, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(OAuth2Authentication auth, @RequestBody HashMap requestData, UriComponentsBuilder ucBuilder) {
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		if(maybeLoggedUser.isPresent()) {
			Long functionalityId = Long.valueOf((Integer) requestData.get("functionality"));
			Optional<Functionality> maybeFunctionality = functionalityService.getOne(functionalityId);
			if (maybeFunctionality.isPresent()) {
				Specification spec = new Specification();
				spec.setCreator(maybeLoggedUser.get());
				spec.setFunctionality(maybeFunctionality.get());
				String value = (String) requestData.get("value");
				spec.setValue(value);
				specificationService.save(spec);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
}
