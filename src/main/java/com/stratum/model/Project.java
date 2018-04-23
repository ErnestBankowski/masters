package com.stratum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	
	@Id	
	@Column(name="project_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="project_owner_id")
	private long projectOwnerId;
	
	@Column(name="project_start")
	private long projectStartTime;
	
	@Column(name="project_end")
	private long projectEndTime;
	
	@Column(name="project_name")
	private String projectName;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProjectOwnerId() {
		return projectOwnerId;
	}

	public void setProjectOwnerId(long projectOwnerId) {
		this.projectOwnerId = projectOwnerId;
	}

	public long getProjectStartTime() {
		return projectStartTime;
	}

	public void setProjectStartTime(long projectStartTime) {
		this.projectStartTime = projectStartTime;
	}

	public long getProjectEndTime() {
		return projectEndTime;
	}

	public void setProjectEndTime(long projectEndTime) {
		this.projectEndTime = projectEndTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "USER [id = "+id+"] "
		+ "[projectOwnerId = "+projectOwnerId+"] "
		+ "[projectStartTime = "+projectStartTime+"] "
		+ "[projectEndTime = "+projectEndTime+"] "
		+ "[projectName = "+projectName+"]";
	}
}