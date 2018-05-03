package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;
import com.stratum.repository.ProjectParticipantRepository;
import com.stratum.repository.ProjectRepository;

@Service
public class ProjectParticipantServiceImpl implements ProjectParticipantService{

	@Autowired
	ProjectParticipantRepository projectParticipantRepository;
	
	
	@Override
	public List<ProjectParticipant> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProjectParticipant> getOne(Long id) {
		return projectParticipantRepository.findById(id);
	}

	@Override
	public void save(ProjectParticipant participant) {
		projectParticipantRepository.save(participant);
	}

	@Override
	public void delete(ProjectParticipant object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<ProjectParticipant> getAllForProject(Long id) {
		return projectParticipantRepository.getAllForProject(id);
	}

	

}
