package com.feem.dao;

import java.util.List;

import com.feem.model.User;

public interface UserDAO {

	List<User> get();
	void insert(User user);
	void update(User user);
	void delete(Integer idUser);
	
}
