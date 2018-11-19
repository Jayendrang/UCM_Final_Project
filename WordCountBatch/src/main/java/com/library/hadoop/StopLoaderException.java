package com.library.hadoop;

public class StopLoaderException extends Exception{

	String exceptionmessage;
	public StopLoaderException(String exceptionstring) {
		super(exceptionstring);
		exceptionmessage=exceptionstring;
	}
	public String toString() {
		return exceptionmessage+"StopLoaderException";
	}
}
