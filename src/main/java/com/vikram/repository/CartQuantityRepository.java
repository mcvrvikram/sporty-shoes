package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.entity.CartQuantity;

public interface CartQuantityRepository extends JpaRepository<CartQuantity, Integer>{

}
