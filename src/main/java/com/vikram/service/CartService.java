package com.vikram.service;

import com.vikram.entity.Cart;
import com.vikram.entity.CartQuantity;
import com.vikram.entity.User;


public interface CartService {
	
	Cart findById(int id);
	Cart saveOrUpdate(CartQuantity cartQuantity, User user);
	

}
