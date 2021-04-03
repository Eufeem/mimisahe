package com.feem.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feem.helper.Constants;
import com.feem.helper.HttpResponse;
import com.feem.model.User;
import com.feem.service.UserService;
import com.google.gson.Gson;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userService;

	@GetMapping
	public List<User> get() {
		return userService.get();
	}
	
	@GetMapping("/{id}")
	public User findByid(@PathVariable Integer id) {
		return userService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody User user) {
		try {
			userService.insert(user);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody User user) {
		try {
			userService.update(user);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer idUser) {
		try {
			userService.delete(idUser);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
