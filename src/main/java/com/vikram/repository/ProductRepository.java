package com.vikram.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikram.entity.Category;
import com.vikram.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByNameContainingIgnoreCase(String name);
	
	List<Product> findByCategory(Category category);
}
