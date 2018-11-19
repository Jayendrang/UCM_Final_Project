package library.app.exceptions;

public class InstitutionExceptions extends Exception{

	private String message;
	
	public InstitutionExceptions(String msg) {
		super(msg);
		this.message=msg;
		
	}
	
	public String toString() {
		return message;
	}
}
