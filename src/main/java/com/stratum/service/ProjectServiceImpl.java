package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;
import com.stratum.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public List<Project> list() {
		return projectRepository.findAll();
	}

	@Override
	public Optional<Project> getOne(Long id) {
		return projectRepository.findById(id);
	}

	@Override
	public void save(Project project) {
		projectRepository.save(project);
	}

	@Override
	public void delete(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		return false;
	}
	
}
