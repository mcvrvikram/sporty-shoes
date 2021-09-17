package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
