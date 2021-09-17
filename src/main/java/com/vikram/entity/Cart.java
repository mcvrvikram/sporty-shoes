package com.vikram.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "cart",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CartQuantity> cartQuantity;
	
	@ManyToOne
	private User user;
	
	@Column
	private boolean isCheckedOut;
	
	@OneToOne(mappedBy = "cart",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Invoice invoice;
	
	@Column
	private int total;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Cart() {
	
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<CartQuantity> getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(Set<CartQuantity> cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public void setCheckedOut(boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}



}
