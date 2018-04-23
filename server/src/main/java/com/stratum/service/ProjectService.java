package com.stratum.service;

import java.util.List;

import com.stratum.model.Project;

public interface ProjectService {
	
	public List<Project> listProjects();
	public Project getProjectById(Long id);
	public void saveProject(Project project);
	public void deleteProject(Project project);
	
}
