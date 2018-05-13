package com.stratum.dto;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stratum.model.Functionality;
import com.stratum.model.Specification;
import com.stratum.model.Sprint;
import com.stratum.model.UseCase;
import com.stratum.model.User;
import com.stratum.service.SprintService;
import com.stratum.service.UserService;

public class FunctionalityDTO {
	
	private Long sprint;
	private String creationDate;
	private Long creator;
	private Long responsibleDeveloper;
	private Long responsibleArchitect;
	private Long responsibleTester;
	private String name;
	private String state;
	private Long specification;
	private Long useCase;
	
	public FunctionalityDTO(LinkedHashMap requestData) {
		sprint = Long.valueOf((Integer) requestData.get("sprint"));
		name = (String) requestData.get("functionalityName");
	}

	public Long getSprint() {
		return sprint;
	}

	public void setSprint(Long sprint) {
		this.sprint = sprint;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getResponsibleDeveloper() {
		return responsibleDeveloper;
	}

	public void setResponsibleDeveloper(Long responsibleDeveloper) {
		this.responsibleDeveloper = responsibleDeveloper;
	}

	public Long getResponsibleArchitect() {
		return responsibleArchitect;
	}

	public void setResponsibleArchitect(Long responsibleArchitect) {
		this.responsibleArchitect = responsibleArchitect;
	}

	public Long getResponsibleTester() {
		return responsibleTester;
	}

	public void setResponsibleTester(Long responsibleTester) {
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

	public Long getSpecification() {
		return specification;
	}

	public void setSpecification(Long specification) {
		this.specification = specification;
	}

	public Long getUseCase() {
		return useCase;
	}

	public void setUseCase(Long useCase) {
		this.useCase = useCase;
	}

}
