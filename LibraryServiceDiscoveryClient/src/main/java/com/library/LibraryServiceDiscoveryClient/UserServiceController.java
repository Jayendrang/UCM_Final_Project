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

import com.library.pojo.User_Info;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController 
@RequestMapping("/library/user")
public class UserServiceController {

	@Autowired
	com.library.service.UserSerivces userService;
	
	@PostMapping("/register")
	public User_Info addNewUser(@Valid @RequestBody User_Info data) {
		return userService.registerUser(data);
	}
	
	@PostMapping("/modify")
	public User_Info modifyUserInfo(@Valid @RequestBody User_Info data) {
		return userService.updateProfile(data);
	}
	
	@PostMapping("/deactivateProfile")
	public boolean deactivateProfile(@Valid @RequestBody User_Info data) {
		return userService.deactivateProfile(data);
	}
	
	@PostMapping("/lockUnlockProfile")
	public boolean lockUnlockProfile(@RequestParam(value="status")String status,@RequestParam(value="userId")String userId) {
		return userService.lockUnlockUserProfile(status, userId);
	}
	
	@GetMapping("/retrieveAllUser")
	public List<User_Info> getAllUserInfo(@RequestParam(value="role")String role){
		return userService.getAllUserDetails(role);
	}

	@GetMapping("/getRecoveryQuestions")
	public User_Info getRecoveryQuestions(@RequestParam(value="id") String id){
		User_Info req = new User_Info();
		req.setUser_id(id);
		return userService.getRecoveryQuestions(req);
	}
	
	@GetMapping("/searchByLastname")
	public List<User_Info> getUserByLastName(@RequestParam(value="lastname")String lname){
		return userService.searchUserInforByLastName(lname);
	}
}
