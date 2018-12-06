package com.library.service;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;

import com.library.pojo.UserCredsInfo;

public interface LibraryUserAuthenticationService {
public UserCredsInfo authenticateUserSession(String info);
public Boolean closeUserSession( String data);

}
