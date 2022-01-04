package com.vengat.tuts.oauthresourceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vengat.tuts.oauthresourceserver.service.NameService;

@RestController
public class HelloController {
	
	@Autowired
	private NameService nameService;
	
	@GetMapping("/hello")
	public String hello(OAuth2Authentication	authentication) {
		
		OAuth2AuthenticationDetails	details = (OAuth2AuthenticationDetails)	authentication.getDetails();
		return "Hello! " + details.getDecodedDetails();
	}
	
	@GetMapping("/name")
	public String name() {
		return "Hi " + nameService.getName();
	}

	@GetMapping("/secret/names/{name}")
	public List<String> names(@PathVariable String name) {
		return nameService.getSecretNames(name);
	}
}
