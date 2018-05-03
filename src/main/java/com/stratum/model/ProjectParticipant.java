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
	private User project;
	
	@Column(name="role")
	private String role;
	
	@Column(name="enroll_time")
	private Date enrollTime;
	
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
		
}
