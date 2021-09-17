package com.vikram.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDetailsDto {
	
	private List<CartDto> cartDtos;
	
	private String line1;
	
	private String line2;
	
	private String zipcode;
	
	private String phone;
	
	private int invoiceId;
	
	private LocalDate date;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public List<CartDto> getCartDtos() {
		return cartDtos;
	}

	public void setCartDtos(List<CartDto> cartDtos) {
		this.cartDtos = cartDtos;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private boolean isDelivered;
	
	private int total;
	
	

}

