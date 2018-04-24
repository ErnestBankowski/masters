package com.stratum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		List<Project> projects = projectService.listProjects();
	       if(projects.isEmpty()){
	            return new ResponseEntity<List<Project> >(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Project> >(projects, HttpStatus.OK);
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createProject(@RequestBody Project project, UriComponentsBuilder ucBuilder) { 
     /*   if (projectService.isProjectExistent(project)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
		projectService.saveProject(project);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(project.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
}
