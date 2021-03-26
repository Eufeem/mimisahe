package com.feem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feem.dao.UserDAO;
import com.feem.model.User;
import com.feem.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired private UserDAO userDAO;

	@Override
	public List<User> get() {
		return userDAO.get();
	}

	@Override
	public void insert(User user) {
		userDAO.insert(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void delete(Integer idUser) {
		userDAO.delete(idUser);
	}

}
