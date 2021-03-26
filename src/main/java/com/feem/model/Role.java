package com.feem.model;

public class Role extends GenericAttributes {

	private Integer idRole;
	private Function function;

	public Role() { super(); }

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

}
