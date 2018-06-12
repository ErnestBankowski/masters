package com.stratum.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;
import com.stratum.repository.ProjectParticipantRepository;
import com.stratum.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectParticipantRepository projectParticipantRepository;
	
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

	@Override
	public List<Project> getForUser(String email) {
		Set<Project> projects = new HashSet<>();
		List<ProjectParticipant> allForUser = projectParticipantRepository.getAllForUser(email);
		for(ProjectParticipant participant : allForUser) {
			projects.add(participant.getProject());
		}
		return new ArrayList<Project>(projects);
	}	
}
