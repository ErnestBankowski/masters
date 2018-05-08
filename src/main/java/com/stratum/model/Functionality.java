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
@Table(name="functionality")
public class Functionality {
	
	@Id	
	@Column(name="functionality_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="creator")
	private User creator;
	
	@ManyToOne
	@JoinColumn(name="responsible_developer")
	private User responsibleDeveloper;
	
	@ManyToOne
	@JoinColumn(name="responsible_architect")
	private User responsibleArchitect;
	
	@ManyToOne
	@JoinColumn(name="responsible_tester")
	private User responsibleTester;
	
	@Column(name="name")
	private String name;
	
	@Column(name="state")
	private String state;
	
	@ManyToOne
	@JoinColumn(name="specification")
	private Specification specification;
	
	@ManyToOne
	@JoinColumn(name="useCase")
	private UseCase useCase;
	
	

}
