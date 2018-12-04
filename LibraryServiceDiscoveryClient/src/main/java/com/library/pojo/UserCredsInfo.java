package com.library.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserCredsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3351659900709655604L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String UID;
	private String message;
	
	@JsonProperty("user_info")
	private user_profile userInfo;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public user_profile getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(user_profile userInfo) {
		this.userInfo = userInfo;
	}
	
	
		
}
