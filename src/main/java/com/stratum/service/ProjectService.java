package com.stratum.service;

import java.util.List;

import com.stratum.model.Project;

public interface ProjectService extends DataService<Project, Long>{
	
	public List<Project> getForUser(String email);
}
