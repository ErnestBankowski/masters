package com.stratum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usecase")
public class UseCase {

	@Id	
	@Column(name="usecase_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="functionality")
	Functionality functionality;
	
	@Column(name="title")
	String title;
	
	@ManyToOne
	@JoinColumn(name="creator")
	User creator;
	
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Functionality getFunctionality() {
		return functionality;
	}

	public void setFunctionality(Functionality functionality) {
		this.functionality = functionality;
	}

	
	
	
}
