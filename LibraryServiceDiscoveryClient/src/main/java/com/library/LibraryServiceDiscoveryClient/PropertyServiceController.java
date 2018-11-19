package com.library.LibraryServiceDiscoveryClient;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.StubClass;
import com.library.pojo.app_properties;
import com.library.service.AppPropertyServices;

@RestController
@RequestMapping("/client/app")
public class PropertyServiceController {

	@Autowired
	AppPropertyServices propertyServices;
	
	
	@GetMapping("/allProperties")
	public List<app_properties> getAllproperties(){
		return propertyServices.getAllProperties();
	}
	
	@PostMapping("/saveProperty")
	public List<app_properties> saveProperties(@Valid @RequestBody List<app_properties> requestdata){
		
		return propertyServices.addNewProperty(requestdata);
	}
	
	@PostMapping("/deleteProperty")
	public String deleteAppProperty(@RequestParam(value="propertyId")String propertyId) {
		System.err.println("Controller:::"+propertyId);
		String response;
		if(propertyServices.deleteProperty(propertyId)) {
			response= "Removed Successfully";
		}else
		{
			response= "Operation failed";
		}
		return response;
	}
	@PostMapping("/modifyProperty")
	public ResponseEntity<?> modifyProperty(@Valid @RequestBody app_properties props) {
		app_properties data=propertyServices.modifyProperty(props);
		if(!data.equals(null)) {
			return ResponseEntity.ok("Modified Successfully");	
		}
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.METHOD_FAILURE);
	}
	
	@GetMapping("/getAllRecoveryQuestions")
	public List<StubClass> getAllRecoveryQuestions(){
		return propertyServices.getRecoveryQuestions();
	}
}
