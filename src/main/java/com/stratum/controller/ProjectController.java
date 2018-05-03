package com.stratum.controller;

import java.util.Date;
import java.util.List;
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

import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;
import com.stratum.model.User;
import com.stratum.service.ProjectParticipantService;
import com.stratum.service.ProjectService;
import com.stratum.service.SessionService;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectParticipantService projectParticipantService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Project>> listProjects() {
		List<Project> projects = projectService.list();
	       if(projects.isEmpty()){
	            return new ResponseEntity<List<Project> >(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Project> >(projects, HttpStatus.OK);
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
        Optional<Project> maybeProject = projectService.getOne(id);
        if (maybeProject.isPresent()) {
        	return new ResponseEntity<Project>(maybeProject.get(), HttpStatus.OK);         
        }
        return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createProject(HttpServletRequest  request, OAuth2Authentication auth, @RequestBody Project project, UriComponentsBuilder ucBuilder) { 
     /*   if (projectService.isProjectExistent(project)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
		 Optional<User> maybeLoggedUser = sessionService.findLoggedUser(auth);
	        if (maybeLoggedUser.isPresent()) {
	        	project.setProjectOwnerId(maybeLoggedUser.get());
	    		projectService.save(project);
	    		ProjectParticipant participant = new ProjectParticipant
	    				.ProjectParticipantBuilder()
	    				.user(maybeLoggedUser.get())
	    				.project(project)
	    				.role(ProjectParticipant.Role.PROJECT_MANAGER.toString())
	    				.date(new Date(System.currentTimeMillis()))
	    				.build();
	    		projectParticipantService.save(participant);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(project.getId()).toUri());
	            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);      
	        } else {
	        	 return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);   
	        }
		
    }
	
}
