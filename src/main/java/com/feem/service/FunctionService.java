package com.feem.service;

import java.util.List;

import com.feem.model.Function;

public interface FunctionService {

	List<Function> get();
	Function findById(Integer id);
	void insert(Function model);
	void update(Function model);
	void delete(Integer id);
	
}
