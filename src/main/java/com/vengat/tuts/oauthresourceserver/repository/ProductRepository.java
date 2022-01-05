package com.vengat.tuts.oauthresourceserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;

import com.vengat.tuts.oauthresourceserver.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@PostFilter("filterObject.owner == authentication.name") //this is not the desired way, it leads to load all the records from the db causing it to run out of memory  and the filter
	List<Product> findProductByNameContains_Wrong(String text);
	
	
	//This requires the bean SecurityEvaluationContextExtension, check the product controller
	@Query("SELECT p FROM PRODUCT p WHERE p.name like %:text% AND p.owner=?#{authentication.name}")
	List<Product> findProductByNameContains(String text);
	
}
