package com.library.pojo;

import java.io.Serializable;

public class StubClass implements Serializable{
	
	private static final long serialVersionUID = -5093129613075256137L;
	
	private String key,value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
