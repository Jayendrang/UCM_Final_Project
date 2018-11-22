package library.pojo;

public class MailResponse {
	private String recieverName;
	private String status;

	public MailResponse(String tReciever, String tStatus) {
		this.recieverName = tReciever;
		this.status = tStatus;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
