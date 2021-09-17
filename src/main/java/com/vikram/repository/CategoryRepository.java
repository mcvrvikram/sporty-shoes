package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
