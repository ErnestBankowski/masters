package com.stratum.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.stratum.messaging.MailClient;
import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;
import com.stratum.model.Sprint;
import com.stratum.model.User;
import com.stratum.service.ProjectParticipantService;
import com.stratum.service.ProjectService;
import com.stratum.service.SessionService;
import com.stratum.service.SprintService;
import com.stratum.service.UserService;

@RestController
@RequestMapping("/sprint")
@CrossOrigin(origins = "http://localhost:4200")
public class SprintController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	SprintService sprintService;

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectParticipantService projectParticipantService;

	@Autowired
	SessionService sessionService;
	
	@Autowired 
	MailClient mailClient;
	
	@RequestMapping(value = "/for/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sprint>> getSprintsForProject(@PathVariable("id") long id) {
		Optional<Project> maybeProject = projectService.getOne(id);
		if (maybeProject.isPresent()) {
			List<Sprint> allForProject = sprintService.getAllForProject(maybeProject.get().getId());
			return new ResponseEntity<List<Sprint>>(allForProject, HttpStatus.OK);
		}
		return new ResponseEntity<List<Sprint>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sprint>> getSprintsForUser(OAuth2Authentication auth) {
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		if (maybeLoggedUser.isPresent()) {
			List<Sprint> sprints = sprintService.getForUser(maybeLoggedUser.get().getEmail());
			return new ResponseEntity<List<Sprint>>(sprints, HttpStatus.OK);
		}
		return new ResponseEntity<List<Sprint>>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sprint> getSprintById(@PathVariable("id") long id) {
		Optional<Sprint> maybeSprint = sprintService.getOne(id);
		if(maybeSprint.isPresent()) {
			return new ResponseEntity<Sprint>(maybeSprint.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Sprint>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createSprint(OAuth2Authentication auth, @RequestBody Object sprintData, UriComponentsBuilder ucBuilder) throws ParseException {
		LinkedHashMap data = (LinkedHashMap) sprintData;
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		Optional<Project> maybeProject = projectService.getOne(Integer.toUnsignedLong((Integer) data.get("project")));
		if (maybeLoggedUser.isPresent() && maybeProject.isPresent()) {
			String sprintName = (String) data.get("sprintName");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 			    
			String start = ((String) data.get("sprintStartTime")).substring(0, 10);
			String end = ((String) data.get("sprintEndTime")).substring(0, 10);
			Date startDate = df.parse( ((String) data.get("sprintStartTime")).substring(0, 10));
			Date endDate =  df.parse( ((String) data.get("sprintEndTime")).substring(0, 10));
			Sprint sprint = new Sprint.SprintBuilder()
					.name(sprintName)
					.project(maybeProject.get())
					.creator(maybeLoggedUser.get())
					.startDate(startDate)
					.endDate(endDate)
					.build();
			sprintService.save(sprint);
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

	}
}
