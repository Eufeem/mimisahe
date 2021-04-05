package com.feem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feem.dao.RoleDAO;
import com.feem.model.Role;
import com.feem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired private RoleDAO roleDAO;

	@Override
	public List<Role> get() {
		return roleDAO.get();
	}
	
	@Override
	public Role findById(Integer id) {
		return roleDAO.findById(id);
	}

	@Override
	public void insert(Role model) {
		roleDAO.insert(model);
	}

	@Override
	public void update(Role model) {
		roleDAO.update(model);
	}

	@Override
	public void delete(Integer id) {
		roleDAO.delete(id);
	}
	
}
