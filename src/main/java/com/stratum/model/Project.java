package com.stratum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	
	@Id	
	@Column(name="project_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="project_owner")
	private User projectOwner;
	
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

	public User getProjectOwnerId() {
		return projectOwner;
	}

	public void setProjectOwnerId(User projectOwner) {
		this.projectOwner = projectOwner;
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
		+ "[projectOwnerId = "+projectOwner.getId()+"] "
		+ "[projectStartTime = "+projectStartTime+"] "
		+ "[projectEndTime = "+projectEndTime+"] "
		+ "[projectName = "+projectName+"]";
	}
}