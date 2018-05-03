package com.stratum.service;

import java.util.List;

import com.stratum.model.Sprint;

public interface SprintService extends DataService<Sprint, Long>{

	public List<Sprint> getAllForProject(Long id);
}
