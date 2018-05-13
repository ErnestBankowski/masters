package com.stratum.service;

import org.springframework.data.jpa.repository.Query;

import com.stratum.model.Specification;
import com.stratum.model.UseCase;

public interface SpecificationService extends DataService<Specification, Long>{

	public Specification getForFunctionality(Long id);
}
