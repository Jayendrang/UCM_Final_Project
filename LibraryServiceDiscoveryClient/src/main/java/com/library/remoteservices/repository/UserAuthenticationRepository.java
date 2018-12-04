package com.library.remoteservices.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.pojo.UserCredsInfo;
import com.library.service.LibraryUserAuthenticationService;


public class UserAuthenticationRepository implements LibraryUserAuthenticationService{

	@Autowired
	RestTemplate restTemplate;
	
	protected final String userRemoteServiceURL = "http://LIBRARY-DAO-SERIVCE".concat("/library").concat("/validator");
	private final String validateLogin = userRemoteServiceURL.concat("/login");
	private final String closeSession = userRemoteServiceURL.concat("/invalidate");
	
	@Override
	public UserCredsInfo authenticateUserSession(String info) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(validateLogin);
		UserCredsInfo data = restTemplate.postForObject(uriBuilder.build().toUriString(), info, UserCredsInfo.class);
		return data;
	}

	@Override
	public String closeUserSession(HttpRequest request) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(validateLogin);
		return restTemplate.postForObject(uriBuilder.build().toUriString(), request, String.class);
	}

}
