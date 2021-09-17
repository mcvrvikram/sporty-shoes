package com.vikram.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Size {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column
	private SizeChart productSize;
	
	@ManyToOne
	private Product product;
	
	public Size() {
		
	}

	@Column
	private int stock;
	
	
	@OneToMany(mappedBy = "productSize")
	private Set<CartQuantity> cart;

	public Set<CartQuantity> getCart() {
		return cart;
	}

	public void setCart(Set<CartQuantity> cart) {
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public SizeChart getProductSize() {
		return productSize;
	}

	public void setProductSize(SizeChart productSize) {
		this.productSize = productSize;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
		
	

}
