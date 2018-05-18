package com.stratum.service;

import java.util.List;

import com.stratum.model.Functionality;

public interface FunctionalityService extends DataService<Functionality, Long>{

	List<Functionality> getAllForSprint(long id);
	List<Functionality> getAllForUser(String email);

}
