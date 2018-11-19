package library.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserExceptions extends Exception{
private String tMessage;
	
public UserExceptions(String message) {
	super(message);
	this.tMessage=message;
}

public String toString() {
	return tMessage;
}

}
