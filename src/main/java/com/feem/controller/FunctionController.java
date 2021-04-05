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
import com.feem.model.Function;
import com.feem.service.FunctionService;
import com.google.gson.Gson;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/function")
public class FunctionController {

	@Autowired private FunctionService functionService;

	@GetMapping
	public List<Function> get() {
		return functionService.get();
	}
	
	@GetMapping("/{id}")
	public Function findByid(@PathVariable Integer id) {
		return functionService.findById(id);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insert(@RequestBody Function model) {
		try {
			functionService.insert(model);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Function model) {
		try {
			functionService.update(model);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		try {
			functionService.delete(id);
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(Constants.MESSAGE_SUCCESS, HttpStatus.OK.value())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Gson().toJson(new HttpResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
