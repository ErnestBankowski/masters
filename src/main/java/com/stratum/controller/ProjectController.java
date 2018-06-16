package com.stratum.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.stratum.model.User;
import com.stratum.service.ProjectParticipantService;
import com.stratum.service.ProjectService;
import com.stratum.service.SessionService;
import com.stratum.service.UserService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectParticipantService projectParticipantService;

	@Autowired
	SessionService sessionService;
	
	@Autowired 
	MailClient mailClient;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Project>> listProjects() {
		List<Project> projects = projectService.list();
		if (projects.isEmpty()) {
			return new ResponseEntity<List<Project>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
		Optional<Project> maybeProject = projectService.getOne(id);
		if (maybeProject.isPresent()) {
			return new ResponseEntity<Project>(maybeProject.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Project>> getProjectForUser(OAuth2Authentication auth) {
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		if (maybeLoggedUser.isPresent()) {
			List<Project> projects = projectService.getForUser(maybeLoggedUser.get().getEmail());
			return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
		}
		return new ResponseEntity<List<Project>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createProject(OAuth2Authentication auth, @RequestBody Project project, UriComponentsBuilder ucBuilder) {
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		if (maybeLoggedUser.isPresent()) {
			project.setProjectOwnerId(maybeLoggedUser.get());
			projectService.save(project);
			ProjectParticipant participant = new ProjectParticipant.ProjectParticipantBuilder()
					.user(maybeLoggedUser.get())
					.project(project)
					.role(ProjectParticipant.Role.PROJECT_MANAGER.toString())
					.date(new Date(System.currentTimeMillis()))
					.build();
			projectParticipantService.save(participant);
			mailClient.prepareAndSend(maybeLoggedUser.get().getEmail(), 
					"Project "+project.getProjectName()+" created", 
					"You have successfully created a project. You are now Project Manager of "+project.getProjectName());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(project.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/saveParticipant", method = RequestMethod.POST)
	public ResponseEntity<Void> storeParticipant(@RequestBody Object participantData, UriComponentsBuilder ucBuilder) {
		LinkedHashMap data = (LinkedHashMap) participantData;
		Optional<User> maybeUser = userService.getOne((String) data.get("participant")); 
		Optional<Project> maybeProject = projectService.getOne(Integer.toUnsignedLong((Integer) data.get("project")));
		if(maybeUser.isPresent() && maybeProject.isPresent()) {
			String role = (String) data.get("role");
			Date enrollTime = new Date(System.currentTimeMillis());
			ProjectParticipant participant = new ProjectParticipant.ProjectParticipantBuilder()
					.user(maybeUser.get())
					.project(maybeProject.get())
					.role(role)
					.date(enrollTime)
					.build();
			projectParticipantService.save(participant);
			mailClient.prepareAndSend(participant.getParticipant().getEmail(), 
					"You have joined project: "+participant.getProject().getProjectName(), 
					"You are now a member of project "+participant.getProject().getProjectName()+". Your role is: "+participant.getRole());
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/participants/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectParticipant>> getParticipants(@PathVariable("id") long id) {
		Optional<Project> maybeProject = projectService.getOne(id);
		if (maybeProject.isPresent()) {
			List<ProjectParticipant> allForProject = projectParticipantService.getAllForProject(maybeProject.get().getId());
			return new ResponseEntity<List<ProjectParticipant>>(allForProject, HttpStatus.OK);
		}
		return new ResponseEntity<List<ProjectParticipant>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getUserRoles(OAuth2Authentication auth, @PathVariable("id") long id) {
		Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
		if (maybeLoggedUser.isPresent()) {
			List<String> roles = projectParticipantService.getUserRoles(maybeLoggedUser.get().getEmail(), id);
			return new ResponseEntity<List<String>>(roles, HttpStatus.OK);
		}			
		return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);	
	}
 
	
	

}
