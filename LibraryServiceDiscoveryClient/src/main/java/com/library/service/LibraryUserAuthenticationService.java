package com.library.service;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

import com.library.pojo.UserCredsInfo;

public interface LibraryUserAuthenticationService {
public UserCredsInfo authenticateUserSession(String info);
public String closeUserSession(HttpRequest request);

}
