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
	
	@ManyToOne
	@JoinColumn(name="sprint")
	private Sprint sprint;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getResponsibleDeveloper() {
		return responsibleDeveloper;
	}

	public void setResponsibleDeveloper(User responsibleDeveloper) {
		this.responsibleDeveloper = responsibleDeveloper;
	}

	public User getResponsibleArchitect() {
		return responsibleArchitect;
	}

	public void setResponsibleArchitect(User responsibleArchitect) {
		this.responsibleArchitect = responsibleArchitect;
	}

	public User getResponsibleTester() {
		return responsibleTester;
	}

	public void setResponsibleTester(User responsibleTester) {
		this.responsibleTester = responsibleTester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public UseCase getUseCase() {
		return useCase;
	}

	public void setUseCase(UseCase useCase) {
		this.useCase = useCase;
	}
	
	

}
