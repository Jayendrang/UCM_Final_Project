package com.library.service;

import java.util.List;

import com.library.pojo.StubClass;
import com.library.pojo.app_properties;

public interface AppPropertyServices {
	
	
	public List<app_properties> addNewProperty(List<app_properties> data);
	public boolean deleteProperty(String propertyId);
	public app_properties modifyProperty(app_properties data);
	public List<app_properties> getAllProperties();
	public List<StubClass> getRecoveryQuestions();
	
	

}
