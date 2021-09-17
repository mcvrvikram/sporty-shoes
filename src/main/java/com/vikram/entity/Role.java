package com.vikram.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Entity
public class Role {
	
	public Role(CustomRole name) {
		this.name=name;
	}

	@Id
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	private CustomRole name;

	public Role() {
		
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CustomRole getName() {
		return name;
	}

	public void setName(CustomRole name) {
		this.name = name;
	}
	

}
