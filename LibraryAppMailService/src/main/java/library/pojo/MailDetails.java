package library.pojo;


public class MailDetails {
	private String reciever;
	private String username;
	private String password;
	private String role;
	
	public MailDetails() {

	}

	public MailDetails(String tReciever, String tUsername, String tPassword) {
		this.reciever = tReciever;
		this.username = tUsername;
		this.password = tPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
