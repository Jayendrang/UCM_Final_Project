package com.library.LibraryServiceDiscoveryClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.UserCredsInfo;
import com.library.service.LibraryUserAuthenticationService;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController 
@RequestMapping("/library/authenticator")
public class UserAuthenticationController {

	@Autowired
	LibraryUserAuthenticationService authenticationSerivce;
	
	@PostMapping("/login")
	public UserCredsInfo authenticateUser(@RequestBody String info) {
		 
		return authenticationSerivce.authenticateUserSession(info); 
	}
	
	@PostMapping("/logoff")
	public String closeUserSession(HttpRequest httpRequest) {
		return authenticationSerivce.closeUserSession(httpRequest);
	}
}
