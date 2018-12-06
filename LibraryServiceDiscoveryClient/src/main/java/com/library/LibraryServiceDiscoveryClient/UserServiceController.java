package com.library.LibraryServiceDiscoveryClient;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.StubClass;
import com.library.pojo.user_profile;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController 
@RequestMapping("/library/user")
public class UserServiceController {

	@Autowired
	com.library.service.UserSerivces userService;
	
	@PostMapping("/register")
	public user_profile addNewUser( @RequestBody user_profile data) {
		return userService.registerUser(data);
	}
	
	@PostMapping("/modify")
	public user_profile modifyUserInfo( @RequestBody user_profile data) {
		System.err.println(data.getRecovery_question1()+"-----"+data.getRecovery_answer1());
		System.err.println(data.getRecovery_question2()+"-----"+data.getRecovery_answer2());
		return userService.updateProfile(data);
	}
	
	@PostMapping("/deactivateProfile")
	public boolean deactivateProfile( @RequestBody user_profile data) {
		return userService.deactivateProfile(data);
	}
	
	@PostMapping("/lockUnlockProfile")
	public boolean lockUnlockProfile(@RequestParam(value="status")String status,@RequestParam(value="userId")String userId) {
		return userService.lockUnlockUserProfile(status, userId);
	}
	
	@GetMapping("/retrieveAllUser")
	public List<user_profile> getAllUserInfo(@RequestParam(value="role")String role){
		return userService.getAllUserDetails(role);
	}

	@GetMapping("/getRecoveryQuestions")
	public user_profile getRecoveryQuestions(@RequestParam(value="id") String id){
		user_profile req = new user_profile();
		req.setUser_id(id);
		return userService.getRecoveryQuestions(req);
	}
	
	@GetMapping("/searchByLastname")
	public List<user_profile> getUserByLastName(@RequestParam(value="lastname")String lname){
		return userService.searchUserInforByLastName(lname);
	}
	
	@GetMapping("/userCount")
	public StubClass getTotalCount() {
		return userService.getTotalCount();
	}
	
	@GetMapping("/userCountByInstitution")
	public StubClass getTotalCountInstitution(@RequestParam(value="institution_id")String inst_id) {
		return userService.getUserCountByInstitution(inst_id);
	}
	
}
