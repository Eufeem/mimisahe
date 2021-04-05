package com.feem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feem.dao.FunctionDAO;
import com.feem.model.Function;
import com.feem.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {

	@Autowired private FunctionDAO functionDAO;

	@Override
	public List<Function> get() {
		return functionDAO.get();
	}
	
	@Override
	public Function findById(Integer id) {
		return functionDAO.findById(id);
	}

	@Override
	public void insert(Function model) {
		functionDAO.insert(model);
	}

	@Override
	public void update(Function model) {
		functionDAO.update(model);
	}

	@Override
	public void delete(Integer id) {
		functionDAO.delete(id);
	}

}
