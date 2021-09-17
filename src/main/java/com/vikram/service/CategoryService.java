package com.vikram.service;

import java.util.List;

import com.vikram.entity.Category;

public interface CategoryService {

	Category findById(int id);
	
	List<Category> findAll();
	
	Category saveOrUpdate(Category Category);
	
	void removeById(int id);
}
