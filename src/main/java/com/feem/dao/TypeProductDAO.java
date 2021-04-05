package com.feem.dao;

import java.util.List;

import com.feem.model.TypeProduct;


public interface TypeProductDAO {
	
	List<TypeProduct> get();
	TypeProduct findById(Integer id);
	void insert(TypeProduct model);
	void update(TypeProduct model);
	void delete(Integer id);

}
