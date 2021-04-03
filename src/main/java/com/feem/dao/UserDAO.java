package com.feem.dao;

import java.util.List;

import com.feem.model.User;

public interface UserDAO {

	List<User> get();
	User findById(Integer id);
	void insert(User model);
	void update(User model);
	void delete(Integer id);
	
}
