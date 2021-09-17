package com.vikram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.entity.Category;
import com.vikram.entity.Product;
import com.vikram.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product findById(int id) {
		
		Optional<Product> findProduct = productRepository.findById(id);
		if(findProduct.isPresent()) {
			return findProduct.get();
		}
		return null;
	}

	@Override
	public List<Product> findAll() {
		
		return productRepository.findAll();
	}

	@Override
	public Product saveOrUpdate(Product product) {
		
		
		return productRepository.save(product);
		
	}

	@Override
	public void removeById(int id) {
		
		productRepository.deleteById(id);
		
	}

	@Override
	public List<Product> findByNameContainingIgnoreCase(String name) {
		
		return productRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		
		return productRepository.findByCategory(category);
		
	}

}
