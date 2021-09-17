package com.vikram.dto;


public class CartDto {

	private int cartQuantityId;
	
	private String productName;
	
	private String size;
	
	private int price;
	
	private int quantity;
	
	private String imgPath;
		
	
	public int getCartQuantityId() {
		return cartQuantityId;
	}

	public void setCartQuantityId(int cartQuantityId) {
		this.cartQuantityId = cartQuantityId;
	}
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartDto() {
		
	}
}
