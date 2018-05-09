package com.stratum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stratum.model.Functionality;
import com.stratum.model.Sprint;
import com.stratum.service.FunctionalityService;
import com.stratum.service.SprintService;

@RestController
@RequestMapping("/functionality")
@CrossOrigin(origins = "http://localhost:4200")
public class FunctionalityController {

	@Autowired
	FunctionalityService functionalityService;
	
	@Autowired
	SprintService sprintService;
	
	@RequestMapping(value = "/for/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Functionality>> getFunctionalitiesForSprint(@PathVariable("id") long id) {
		Optional<Sprint> maybeSprint = sprintService.getOne(id);
		if (maybeSprint.isPresent()) {
			List<Functionality> allForSprint = functionalityService.list();
					// functionalityService.getAllForSprint(maybeSprint.get().getId());
			return new ResponseEntity<List<Functionality>>(allForSprint, HttpStatus.OK);
		}
		return new ResponseEntity<List<Functionality>>(HttpStatus.NOT_FOUND);
	}
}
