package com.stratum.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="project_participant")
public class ProjectParticipant {

	@Id	
	@Column(name="project_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="participant")
	private User participant;
	
	@ManyToOne
	@JoinColumn(name="project")
	private Project project;
	
	@Column(name="role")
	private String role;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name="enroll_time")
	private Date enrollTime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}

	public ProjectParticipant() {
		
	}
	
	private ProjectParticipant(ProjectParticipantBuilder builder) {
		this.participant =  builder.participant;
		this.project =  builder.project;
		this.role =  builder.role;
		this.enrollTime = builder.enrollTime;
	}
	
	public static enum Role{
		PROJECT_MANAGER {
			public String toString() {
		          return "Project Manager";
		      }
		},
		ARCHITECT {
			public String toString() {
		          return "Architect";
		      }
		},
		DEVELOPER {
			public String toString() {
		          return "Developer";
		      }
		},
		TESTER {
			public String toString() {
		          return "Tester";
		      }
		}
	}
	
	public static class ProjectParticipantBuilder {
		private User participant;
		private Project project;
		private String role;
		private Date enrollTime;
		
		public ProjectParticipantBuilder() {

		}
		
		public ProjectParticipantBuilder user(User participant) {
			this.participant = participant;
			return this;
		}
		
		public ProjectParticipantBuilder project(Project project) {
			this.project = project;
			return this;
		}
		
		public ProjectParticipantBuilder role(String role) {
			this.role = role;
			return this;
		}
		
		public ProjectParticipantBuilder date(Date enrollTime) {
			this.enrollTime = enrollTime;
			return this;
		}
		
		public ProjectParticipant build() {
			return new ProjectParticipant(this);
		}
		
		
	}
		
}
