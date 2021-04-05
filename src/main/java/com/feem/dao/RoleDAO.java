package com.feem.dao;

import java.util.List;

import com.feem.model.Role;

public interface RoleDAO {

	List<Role> get();
	Role findById(Integer id);
	void insert(Role model);
	void update(Role model);
	void delete(Integer id);
	
}
