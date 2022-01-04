package com.vengat.tuts.oauthresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vengat.tuts.oauthresourceserver.model.Document;
import com.vengat.tuts.oauthresourceserver.service.DocumentService;

@RestController
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@GetMapping("/documents/{code}")
	public Document getDetails(@PathVariable String code) {
		return documentService.getDocumentPostAuthorized(code);
	}
	
	@GetMapping("/docs/{code}")
	public Document getDets(@PathVariable String code) {
		return documentService.getDocumentPreAuthorized(code);
	}
}
