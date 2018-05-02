package com.stratum.service;

import java.util.List;
import java.util.Optional;

public interface DataService<T, K> {
	
	public List<T> list();
	public Optional<T> getOne(K id);
	public void save(T object);
	public void delete(T object);
	public boolean exists(K id);
	
}
