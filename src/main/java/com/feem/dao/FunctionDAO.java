package com.feem.dao;

import java.util.List;

import com.feem.model.Function;

public interface FunctionDAO {

	List<Function> get();
	Function findById(Integer id);
	void insert(Function model);
	void update(Function model);
	void delete(Integer id);
	
}
