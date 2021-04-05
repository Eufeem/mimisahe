package com.feem.service;

import java.util.List;

import com.feem.model.Role;

public interface RoleService {

	List<Role> get();
	Role findById(Integer id);
	void insert(Role model);
	void update(Role model);
	void delete(Integer id);
	
}
