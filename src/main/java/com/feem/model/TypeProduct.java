package com.feem.model;

import java.util.List;

public class TypeProduct extends GenericAttributes {

	private Integer idTypeProduct;
	private List<TypeProductDetail> typeProductDetail;

	public TypeProduct() { super(); }

	public Integer getIdTypeProduct() {
		return idTypeProduct;
	}

	public void setIdTypeProduct(Integer idTypeProduct) {
		this.idTypeProduct = idTypeProduct;
	}

	public List<TypeProductDetail> getTypeProductDetail() {
		return typeProductDetail;
	}

	public void setTypeProductDetail(List<TypeProductDetail> typeProductDetail) {
		this.typeProductDetail = typeProductDetail;
	}
	
}
