package com.feem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.feem.model.Role;
import com.feem.service.RoleService;
import com.google.gson.Gson;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired private RoleService roleService;

	@GetMapping
	public List<Role> get() {
		return roleService.get();
	}
	
	@GetMapping("/{id}")
	public Role findByid(@PathVariable Integer id) {
		return roleService.findById(id);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insert(@RequestBody Role model) {
		try {
			roleService.insert(model);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Role model) {
		try {
			roleService.update(model);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		try {
			roleService.delete(id);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
