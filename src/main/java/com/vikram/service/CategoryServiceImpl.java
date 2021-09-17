package com.vikram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.entity.Category;
import com.vikram.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category findById(int id) {
		
		Optional<Category> findCategory = categoryRepository.findById(id);
		if(findCategory.isPresent()) {
			return findCategory.get();
		}
		return null;
	}

	@Override
	public List<Category> findAll() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category saveOrUpdate(Category category) {
		
		return categoryRepository.save(category);
		
	}

	@Override
	public void removeById(int id) {
		
		categoryRepository.deleteById(id);
		
	}

}
