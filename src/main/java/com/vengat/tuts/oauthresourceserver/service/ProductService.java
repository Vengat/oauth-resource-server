package com.vengat.tuts.oauthresourceserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;

import com.vengat.tuts.oauthresourceserver.model.Product;

public class ProductService {

	@PreFilter("filterObject.owner == authentication.time")	// The list given as a parameter allows only products owned by the authenticated user.
	public List<Product> sellProducts(List<Product> products) {
		//sell products and return the sold products list
		return products;
	}
	
	@PostFilter("filterObject.owner == authentication.name")
	public List<Product> findProducts() {
		List<Product> products = new ArrayList<>();
		
		products.add(new Product("beer", "nikolai"));
	    products.add(new Product("candy", "nikolai"));
	    products.add(new Product("chocolate", "julien"));
	    
	    return products;
		
	}
}
