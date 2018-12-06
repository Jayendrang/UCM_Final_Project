package com.library.LibraryServiceDiscoveryClient;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.InstitutionInfo;
import com.library.pojo.StubClass;
import com.library.service.InstitutionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/library/institution")
public class InstitutionController {
	
	@Autowired
	private InstitutionService institutionService;
	
	@GetMapping("/get")
	public InstitutionInfo getOneInstitutionDetail(@RequestParam(value="id")String institutionId) {
		return institutionService.getInstitutionInfo(institutionId);
	}
	
	@GetMapping("/getAll")
	public List<InstitutionInfo> getAllInstitutionInfo(@RequestParam(value="status") String status){
		return institutionService.getAllInstitutionInfo();
		
	}
	
	@PostMapping("/register")
	public InstitutionInfo registerInstitution(@RequestBody InstitutionInfo info) {
		return institutionService.saveNewInstitution(info);
	}
	
	@PostMapping("/updateStatus")
	public boolean updateInstitutionStatus(@RequestBody String body) throws Exception {
		System.err.println(body);
		
		boolean response = institutionService.updateInstitutionStatus(body);
	
		return response;
	}
	
	@PostMapping("/updateProfile")
	public InstitutionInfo updateInstitutionInfo(@RequestBody InstitutionInfo Institutiondata) {
		return institutionService.updateInstitutionProfile(Institutiondata);
	}
	
	@GetMapping("/totalCount")
	public StubClass getTotalCount(HttpSession session ) {
	
		return institutionService.getTotalCount();
	}
	


}
