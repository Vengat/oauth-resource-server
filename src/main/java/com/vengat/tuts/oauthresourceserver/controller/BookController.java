package com.vengat.tuts.oauthresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vengat.tuts.oauthresourceserver.model.Employee;
import com.vengat.tuts.oauthresourceserver.service.BookService;

public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/book/details/{name}")
	public Employee getDetails(@PathVariable String name) {
		return bookService.getBookDetails(name);
	}
	
	

}
