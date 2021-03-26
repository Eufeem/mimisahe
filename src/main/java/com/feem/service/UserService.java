package com.feem.service;

import java.util.List;

import com.feem.model.User;

public interface UserService {

	List<User> get();
	void insert(User user);
	void update(User user);
	void delete(Integer idUser);
	
}
