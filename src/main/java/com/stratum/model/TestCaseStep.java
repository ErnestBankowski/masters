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
@Table(name = "test_case_step")
public class TestCaseStep {
	
	@Id	
	@Column(name="test_case_step_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@Column(name="name")
	String name;
	
	@Column(name="test_result")
	String testResult;
	
	@ManyToOne
	@JoinColumn(name="usecase")
	UseCase useCase;

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UseCase getUseCase() {
		return useCase;
	}

	public void setUseCase(UseCase useCase) {
		this.useCase = useCase;
	}

	public static enum State{
		NEW {
			public String toString() {
		          return "NEW";
		      }
		},
		PASSED {
			public String toString() {
		          return "PASSED";
		      }
		},
		FAILED {
			public String toString() {
		          return "FAILED";
		      }
		}
	}
}
