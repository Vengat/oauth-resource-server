package com.vengat.tuts.oauthresourceserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vengat.tuts.oauthresourceserver.model.Product;
import com.vengat.tuts.oauthresourceserver.repository.ProductRepository;
import com.vengat.tuts.oauthresourceserver.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	 private ProductRepository productRepository;
	
	@PostMapping("/sell")
	public List<Product> sellProduct() {
		
		List<Product> products = new ArrayList<>();
		
		products.add(new Product("beer", "nikolai"));
	    products.add(new Product("candy", "nikolai"));
	    products.add(new Product("chocolate", "julien"));
	    
	    return productService.sellProducts(products);
	    
	    /**
	     * curl -u julien:12345 http://localhost:8080/sell
			The response body is

			[
  				{"name":"chocolate","owner":"julien"}
			]
	     */
	}
	
	@GetMapping("/find")
	  public List<Product> findProducts() {
	    return productService.findProducts();
	  }
	
	@GetMapping("/products/{text}")
	public List<com.vengat.tuts.oauthresourceserver.entity.Product> findProductsContaining(@PathVariable String text) {
		return productRepository.findProductByNameContains(text);
	}
}
