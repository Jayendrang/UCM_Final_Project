package com.library.remoteservices.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.library.pojo.StubClass;
import com.library.pojo.app_properties;
import com.library.service.AppPropertyServices;

public class AppPropertiesRepository implements AppPropertyServices{

	@Autowired
	protected RestTemplate restTemplate;
	
	protected String propertyRemoteServiceURL="http://LIBRARY-DAO-SERIVCE".concat("/library").concat("/appSettings");
	private String saveProperty="/saveProperty";
	private String getAllProperty="/GetAllProperties";
	private String removeProperty="/removeProperty";
	private String modifyProperty="/updateProperty";
	private String getRecoveryQuestion="/GetRecoveryQuestions";
	
	public AppPropertiesRepository() {
			System.err.println("SYSTEM PROPERTIES SERVICE READY");
	}
	
	@Override
	public List<app_properties> getAllProperties() {
		//app_properties [] props = restTemplate.getForObject(url,app_properties[].class);
		String uri=propertyRemoteServiceURL.concat(getAllProperty);
		System.err.println(uri);
		List<app_properties> props = restTemplate.getForObject(uri, List.class);
		return props;
	}

	@Override
	public List<app_properties> addNewProperty(List<app_properties> data) {
		
		List<app_properties> response = restTemplate.postForObject(propertyRemoteServiceURL.concat(saveProperty), data, List.class);
		return response;
	}

	@Override
	public app_properties modifyProperty(app_properties data) {
		// TODO Auto-generated method stub
		app_properties props=restTemplate.postForObject(propertyRemoteServiceURL.concat(modifyProperty), data,app_properties.class);
		return props;
	}

	@Override
	public List<StubClass> getRecoveryQuestions() {
		// TODO Auto-generated method stub
		System.err.println(propertyRemoteServiceURL.concat(getRecoveryQuestion));
		List<StubClass> data = restTemplate.getForObject(propertyRemoteServiceURL.concat(getRecoveryQuestion), List.class);
		return data;
	}

	@Override
	public boolean deleteProperty(String propertyId) {
		// TODO Auto-generated method stub
		boolean response = restTemplate.postForObject(propertyRemoteServiceURL.concat(removeProperty), propertyId, Boolean.class);
		return response;
	}
	
	 
}
