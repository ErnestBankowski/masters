package com.stratum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.ProjectParticipant;
import com.stratum.repository.ProjectParticipantRepository;

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
	
	@Override
	public List<ProjectParticipant> getAllForProject(Long id) {
		return projectParticipantRepository.getAllForProject(id);
	}

	@Override
	public List<ProjectParticipant> getAllForUser(String email) {
		return projectParticipantRepository.getAllForUser(email);
	}

	@Override
	public List<String> getUserRoles(String email, Long id) {
		List<String> roles = new ArrayList<>();
		List<ProjectParticipant> entries = projectParticipantRepository.getParticipantEntriesForProject(id, email);
		for(ProjectParticipant participant : entries) {
			roles.add(participant.getRole());
		}
		return roles;
	}
	
	

	

}
