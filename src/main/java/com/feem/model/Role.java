package com.feem.model;

public class Role extends GenericAttributes {

	private Integer idRole;
	private Integer status;

	public Role() { super(); }

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
