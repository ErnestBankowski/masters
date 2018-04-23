package com.stratum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.Project;
import com.stratum.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public List<Project> listProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProject(Project project) {
		projectRepository.save(project);
	}

	@Override
	public void deleteProject(Project project) {
		// TODO Auto-generated method stub
		
	}

}
