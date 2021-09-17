package com.vikram.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl {
	
	@PersistenceContext
	EntityManager entityManager;

}
