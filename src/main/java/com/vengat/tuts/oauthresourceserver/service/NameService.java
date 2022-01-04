package com.vengat.tuts.oauthresourceserver.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {

	@PreAuthorize("hasAuthority('write')")
	public String getName() {
		return "Fantastico";
	}
	
	
	@PreAuthorize("hasAnyAuthority")
	public String getNameOfCity() {
		return "Bangalore";
	}
	
	@PreAuthorize("hasRole('admin')")
	public String[] getEmployeesName() {
		return new String[]  {"Amar", "Akbar", "Antony"};
	}
	
	@PreAuthorize("hasAnyRole()")
	public String getWeather() {
		return "pleasant";
	}
	
	private Map<String, List<String>> secretNames = Map.of("natalie", List.of("Energico", "Perfecto"),
																						"emma", List.of("Fantastico"));
	
	@PreAuthorize("#name == authentication.principal.username")
	public List<String> getSecretNames(String name) {
		return secretNames.get(name);
	}
}
