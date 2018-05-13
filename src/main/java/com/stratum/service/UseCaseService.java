package com.stratum.service;

import org.springframework.data.jpa.repository.Query;

import com.stratum.model.UseCase;

public interface UseCaseService extends DataService<UseCase, Long>{

	public UseCase getForFunctionality(Long id);
	
}
