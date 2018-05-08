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
	
	@Column(name="value")
	String value;
	
	@ManyToOne
	@JoinColumn(name="functionality")
	Functionality functionality;
	
	@Column(name="isCompleted", columnDefinition = "boolean default false")
	Boolean isCompleted;
	
	
	
}
