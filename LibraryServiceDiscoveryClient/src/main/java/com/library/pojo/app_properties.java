package com.library.pojo;

import java.io.Serializable;
import java.util.Date;

public class app_properties implements Serializable{

	private static final long serialVersionUID = 1L;

	private String property_id;
	
	private String app_property;

	
	private Date created_on;

	private Date updated_on;

	private String modified_by;

	private String created_by;

	public String getProperty_id() {
		return property_id;
	}

	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	public String getApp_property() {
		return app_property;
	}

	public void setApp_property(String app_property) {
		this.app_property = app_property;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	

}
