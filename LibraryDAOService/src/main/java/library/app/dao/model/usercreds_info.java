package library.app.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class usercreds_info implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5076938519747332372L;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String UID;
	private String message;
	
	@JsonProperty("user_info")
	private user_profile user_profile;
	
	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public user_profile getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(user_profile user_profile) {
		this.user_profile = user_profile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
}
