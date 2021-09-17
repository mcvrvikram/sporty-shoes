package com.vikram.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	String name;
	
	@Column
	String image;
	
	@Column
	String price;
	
	@Enumerated(EnumType.STRING)
	@Column
	Gender gender;
	
	@ManyToOne(targetEntity = Category.class,cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	Category category;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Size> size;
	
	public Set<Size> getSize() {
		return size;
	}

	public void setSize(Set<Size> size) {
		this.size = size;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	 @Transient
    public String getImagePath() {
        if (image == null || id == 0) return null;
         
        return "/uploads/" + id + "/" + image;
    }
	

}
