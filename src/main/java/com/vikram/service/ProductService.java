package com.vikram.service;

import java.util.List;

import com.vikram.entity.Category;
import com.vikram.entity.Product;

public interface ProductService {
	
	Product findById(int id);
	
	List<Product> findAll();
	
	Product saveOrUpdate(Product product);
	
	void removeById(int id);
	
	List<Product> findByNameContainingIgnoreCase(String name);
	
	List<Product> findByCategory(Category category);
}
