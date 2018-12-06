package library.pojo;


public class MailDetails {
	private String mailid;
	private String userid;
	private String password;
	private String role;
	
	public MailDetails() {

	}

	public MailDetails(String tReciever, String tUsername, String tPassword,String role) {
		this.mailid = tReciever;
		this.userid = tUsername;
		this.password = tPassword;
		this.role= role;
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
