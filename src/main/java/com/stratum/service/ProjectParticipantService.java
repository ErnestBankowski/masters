package com.stratum.service;

import java.util.List;

import com.stratum.model.ProjectParticipant;

public interface ProjectParticipantService extends DataService<ProjectParticipant, Long>{

	public List<ProjectParticipant> getAllForProject(Long id);
	public List<ProjectParticipant> getAllForUser(String email);

}
