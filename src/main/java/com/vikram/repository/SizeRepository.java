package com.vikram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikram.entity.Product;
import com.vikram.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {

	
	List<Size> findByProduct(Product product);
	
}
