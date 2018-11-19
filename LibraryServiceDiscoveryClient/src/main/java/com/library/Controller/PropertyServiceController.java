package com.library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.app_properties;
import com.library.service.AppPropertyServices;

@RestController
@RequestMapping("/clientservice")
public class PropertyServiceController {

	@Autowired
	AppPropertyServices propertyServices;
	
	@GetMapping("/sample")
	public String sampleMsg(@RequestParam(value="name")String jay) {
		return jay + "BadAss";
	}
	
	@GetMapping("/allProperties")
	public List<app_properties> getAllproperties(){
		return propertyServices.getAllProperties();
	}
	
}
