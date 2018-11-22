package library.app.LibraryDAOService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.app.dao.model.StubClass;
import library.app.dao.model.app_properties;
import library.app.services.ApplicationPropertiesService;
import library.app.utilities.UniqueIdGenerator;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/library/appSettings")
public class ApplicationPropertyServicesImpl {
	
	@Autowired
	private ApplicationPropertiesService appService;
	
	@SuppressWarnings("unused")
	private void ApplicationPropertySerivce() {
		

	}
	
	@PostMapping("/saveProperty")
	public List<app_properties> saveProperties(@Valid @RequestBody List<app_properties> properties) {
		
		List<app_properties> data = new ArrayList<>();
		for(app_properties props : properties) {
			props.setProperty_id(UniqueIdGenerator.getRandomID());
			System.err.println(props.getProperty_id());
			if(!appService.existsById(props.getProperty_id())) {
			data.add(appService.save(props));
			}
		}
		return data;
	}
	@PostMapping("/removeProperty")
	public boolean removeProperty(@RequestParam(value="propertyId") String propertyId) {
		System.out.println("APP DAO::"+propertyId);
		appService.deleteById(propertyId);
		 if(appService.existsById(propertyId)) {
			 System.err.println("DAO:: PropertyRemoved");
			 return true;
		 }else
		 {
			 return false;
		 }
		
	}
	@PostMapping("/updateProperty")
	public app_properties updateProperty(@Valid @RequestBody app_properties property ) {
		if(appService.existsById(property.getProperty_id())) {
			return appService.save(property);
		}
		return null;
	}
	
	@GetMapping("/GetAllProperties")
	public List<app_properties> getAllProperties(){
		return appService.findAll();
	}
	
	@GetMapping("/GetRecoveryQuestions")
	public List<StubClass> getRecoveryQuestions(){
		String data[]=appService.getProperties();
		List<StubClass> response = new ArrayList<>();
		for(String questions:data) {
			StubClass tempData = new StubClass();
			String[] tempOpt = questions.split(",");
			tempData.setKey(tempOpt[0]);
			tempData.setValue(tempOpt[1]);
			response.add(tempData);
		}
		return response;
	}
	
	
}
