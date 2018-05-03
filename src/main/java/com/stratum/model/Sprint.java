package com.stratum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stratum.model.ProjectParticipant.ProjectParticipantBuilder;

@Entity
@Table(name="sprint")
public class Sprint {

	@Id	
	@Column(name="project_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="creator")
	private User creator;
	
	@ManyToOne
	@JoinColumn(name="project")
	private Project project;
	
	@Column(name="sprint_start")
	private Date sprintStartTime;
	
	@Column(name="sprint_end")
	private Date sprintEndTime;
	
	@Column(name="sprint_name")
	private String sprintName;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getSprintStartTime() {
		return sprintStartTime;
	}

	public void setSprintStartTime(Date sprintStartTime) {
		this.sprintStartTime = sprintStartTime;
	}

	public Date getSprintEndTime() {
		return sprintEndTime;
	}

	public void setSprintEndTime(Date sprintEndTime) {
		this.sprintEndTime = sprintEndTime;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}
	
	public Sprint() {
		
	}
	
	private Sprint(SprintBuilder builder) {
		this.creator = builder.creator;
		this.project = builder.project;
		this.sprintStartTime = builder.sprintStartTime;
		this.sprintEndTime = builder.sprintEndTime;
		this.sprintName = builder.sprintName;
	}
	
	public static class SprintBuilder {
		
		private User creator;
		private Project project;
		private Date sprintStartTime;
		private Date sprintEndTime;
		private String sprintName;
		
		public SprintBuilder() {

		}
		
		public SprintBuilder creator(User creator) {
			this.creator = creator;
			return this;
		}
		
		public SprintBuilder startDate(Date sprintStartTime) {
			this.sprintStartTime = sprintStartTime;
			return this;
		}
		
		public SprintBuilder endDate(Date sprintEndTime) {
			this.sprintEndTime = sprintEndTime;
			return this;
		}
		
		public SprintBuilder project(Project project) {
			this.project = project;
			return this;
		}
		
		public SprintBuilder name(String sprintName) {
			this.sprintName = sprintName;
			return this;
		}
		
		
		public Sprint build() {
			return new Sprint(this);
		}
		
		
	}
}
