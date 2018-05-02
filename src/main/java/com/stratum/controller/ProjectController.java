package com.stratum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.stratum.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Project>> listProjects() {
		System.out.println("a projekty działają...");
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
    public ResponseEntity<Void> createProject(OAuth2Authentication auth, @RequestBody Project project, UriComponentsBuilder ucBuilder) { 
     /*   if (projectService.isProjectExistent(project)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
		projectService.save(project);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(project.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
}
