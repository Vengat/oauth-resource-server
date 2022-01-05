package com.vengat.tuts.oauthresourceserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String owner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Product(String name, String owner) {
		super();
		this.name = name;
		this.owner = owner;
	}
	
	public Product(com.vengat.tuts.oauthresourceserver.model.Product product) {
		this.name = product.getName();
		this.owner = product.getOwner();
	}	

}
