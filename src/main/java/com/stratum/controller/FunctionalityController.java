package com.stratum.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.stratum.dto.FunctionalityDTO;
import com.stratum.messaging.MailClient;
import com.stratum.model.Functionality;
import com.stratum.model.Project;
import com.stratum.model.Sprint;
import com.stratum.model.User;
import com.stratum.service.FunctionalityService;
import com.stratum.service.ProjectService;
import com.stratum.service.SessionService;
import com.stratum.service.SprintService;

@RestController
@RequestMapping("/functionality")
@CrossOrigin(origins = "http://localhost:4200")
public class FunctionalityController {

	@Autowired
	FunctionalityService functionalityService;
	
	@Autowired
	SprintService sprintService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired SessionService sessionService;
	
	@Autowired 
	MailClient mailClient;
	
	@RequestMapping(value = "/for/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Functionality>> getFunctionalitiesForSprint(@PathVariable("id") long id) {
		Optional<Sprint> maybeSprint = sprintService.getOne(id);
		if (maybeSprint.isPresent()) {
			List<Functionality> allForSprint = functionalityService.getAllForSprint(maybeSprint.get().getId());
			return new ResponseEntity<List<Functionality>>(allForSprint, HttpStatus.OK);
		}
		return new ResponseEntity<List<Functionality>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Functionality>> getFunctionalityForUser(OAuth2Authentication auth) {
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		if (maybeLoggedUser.isPresent()) {
			List<Functionality> allForUser = functionalityService.getAllForUser(maybeLoggedUser.get().getEmail());
			return new ResponseEntity<List<Functionality>>(allForUser, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<List<Functionality>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Functionality>> getFunctionalitiesForProject(@PathVariable("id") long id) {
		Optional<Project> maybeProject = projectService.getOne(id);
		if (maybeProject.isPresent()) {
			List<Functionality> allForProject = functionalityService.getAllForProject(maybeProject.get().getId());
			return new ResponseEntity<List<Functionality>>(allForProject, HttpStatus.OK);
		}
		return new ResponseEntity<List<Functionality>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Functionality> getFunctionality(@PathVariable("id") long id) {
		Optional<Functionality> maybeFunctionality = functionalityService.getOne(id);
		if(maybeFunctionality.isPresent()) {
			return new ResponseEntity<Functionality>(maybeFunctionality.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Functionality>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createFunctionality(OAuth2Authentication auth, @RequestBody LinkedHashMap requestData, UriComponentsBuilder ucBuilder) {
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		if (maybeLoggedUser.isPresent()) {
			FunctionalityDTO dto = new FunctionalityDTO(requestData);
			Functionality functionality = new Functionality.FunctionalityBuilder()
			.sprint(sprintService.getOne(dto.getSprint()).get())
			.creationDate(new Date(System.currentTimeMillis()))
			.creator(maybeLoggedUser.get())
			.name(dto.getName())
			.state(Functionality.State.NEW.toString())
			.build();
			functionalityService.save(functionality);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(functionality.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateFunctionality(@RequestBody Functionality functionality, UriComponentsBuilder ucBuilder) {
		functionalityService.save(functionality);
		if(functionality.getResponsibleArchitect() !=null){
		mailClient.prepareAndSend(functionality.getResponsibleArchitect().getEmail(), 
				"Functionality: "+functionality.getId()+" - "+functionality.getName()+" updated.", 
				"Functionality: "+functionality.getId()+" - "+functionality.getName()+" updated. You received this message because You are the responsible Architect. \n"
						+ "Functionality is now in state: "+functionality.getState()+"\n" 
						+ "This functionality is assigned to sprint: "+functionality.getSprint().getId()+ " - "+functionality.getSprint().getSprintName()+" of project: "+functionality.getSprint().getProject().getProjectName());
		}
		if(functionality.getResponsibleDeveloper() !=null){
		mailClient.prepareAndSend(functionality.getResponsibleDeveloper().getEmail(), 
				"Functionality: "+functionality.getId()+" - "+functionality.getName()+" updated.", 
				"Functionality: "+functionality.getId()+" - "+functionality.getName()+" updated. You received this message because You are the responsible Developer. \n"
						+ "Functionality is now in state: "+functionality.getState()+"\n" 
						+ "This functionality is assigned to sprint: "+functionality.getSprint().getId()+ " - "+functionality.getSprint().getSprintName()+" of project: "+functionality.getSprint().getProject().getProjectName());
		}
		if(functionality.getResponsibleTester() !=null){
		mailClient.prepareAndSend(functionality.getResponsibleTester().getEmail(), 
				"Functionality: "+functionality.getId()+" - "+functionality.getName()+" updated.", 
				"Functionality: "+functionality.getId()+" - "+functionality.getName()+" updated. You received this message because You are the responsible Tester. \n"
						+ "Functionality is now in state: "+functionality.getState()+"\n" 
						+ "This functionality is assigned to sprint: "+functionality.getSprint().getId()+ " - "+functionality.getSprint().getSprintName()+" of project: "+functionality.getSprint().getProject().getProjectName());
		}
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	} 
}
