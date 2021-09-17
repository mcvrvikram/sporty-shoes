//package com.vikram.entity;
//
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table
//public class Address {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	@Column
//	private String line1;
//	
//	@Column
//	private String line2;
//	
//	@Column
//	private String zipcode;
//	
//	@ManyToOne
//	private User user;
//	
//	@OneToMany(mappedBy = "address")
//	private Set<Invoice> invoice;
//	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//
//	public Set<Invoice> getInvoice() {
//		return invoice;
//	}
//
//	public void setInvoice(Set<Invoice> invoice) {
//		this.invoice = invoice;
//	}
//
//	public Address() {
//		
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getLine1() {
//		return line1;
//	}
//
//	public void setLine1(String line1) {
//		this.line1 = line1;
//	}
//
//	public String getLine2() {
//		return line2;
//	}
//
//	public void setLine2(String line2) {
//		this.line2 = line2;
//	}
//
//	public String getZipcode() {
//		return zipcode;
//	}
//
//	public void setZipcode(String zipcode) {
//		this.zipcode = zipcode;
//	}
//	
//	
//}
