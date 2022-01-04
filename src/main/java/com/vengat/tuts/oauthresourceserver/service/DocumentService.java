package com.vengat.tuts.oauthresourceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.vengat.tuts.oauthresourceserver.model.Document;
import com.vengat.tuts.oauthresourceserver.repository.DocumentRepository;

public class DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@PostAuthorize("hasPermission(returnObject, 'ROLE_ADMIN')")
	public Document getDocumentPostAuthorized(String code) {
		return documentRepository.findDocument(code);
	}
	
	@PreAuthorize("hasPermission(#code, 'document', 'ROLE_ADMIN')")
	public Document getDocumentPreAuthorized(String code) {
		return documentRepository.findDocument(code);
	}

}
