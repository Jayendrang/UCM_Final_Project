package com.library.pojo;


public class MailDetails {
	private String mailid;
	private String userid;
	private String password;
	private String role;
	
	public MailDetails() {

	}

	public MailDetails(String mailid,String tReciever, String tUsername, String tPassword) {
		this.mailid = tReciever;
		this.userid = tUsername;
		this.password = tPassword;
		this.mailid= mailid;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



}
