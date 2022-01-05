package com.vengat.tuts.oauthresourceserver.model;

public class Product {
	
	private String owner;
	
	private String name;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product(String owner, String name) {
		super();
		this.owner = owner;
		this.name = name;
	}
	
	

}
