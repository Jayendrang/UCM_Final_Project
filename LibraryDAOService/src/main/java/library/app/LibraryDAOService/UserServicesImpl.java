package library.app.LibraryDAOService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import library.app.dao.model.StubClass;
import library.app.dao.model.user_profile;
import library.app.exceptions.UserExceptions;
import library.app.services.UserServices;
import library.app.utilities.AppConstants;
import library.app.utilities.UniqueIdGenerator;

@RestController
@RequestMapping("/library/user")
public class UserServicesImpl {

	@Autowired
	private UserServices userservices;
	
	//private BCryptPasswordEncoder passwordEncoder;

	// Register new user
	@PostMapping("/register")
	public user_profile registerNewUserProfile(@Valid @RequestBody user_profile user) throws Exception {
		user.setUser_id(UniqueIdGenerator.getRandomUserID(user.getUser_fname(), user.getUser_lname()));
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setStatus(AppConstants.ACTIVE_USER_STATUS);
		if (!userservices.existsById(user.getUser_id())) {
			return userservices.save(user);
		}
		return null;
	}

	//Library and Admin User registeration
	@PostMapping("/register/admin")
	public user_profile registerNewLibraryUserProfile(@Valid @RequestBody user_profile user) throws Exception {
		user.setUser_id(UniqueIdGenerator.getRandomUserID(user.getUser_fname(), user.getUser_lname()));
	//	user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setStatus(AppConstants.ACTIVE_USER_STATUS);
		if (!userservices.existsById(user.getUser_id())) {
			return userservices.save(user);
		}
		return null;
	}

	// Update user profile
	@PutMapping("/updateuserprofile")
	public user_profile updateUserProfile(@Valid @RequestBody user_profile user) throws Exception {
		if (userservices.existsById(user.getUser_id())) {
			return userservices.save(user);
		}
		return null;
	}

	// Deactivate user profile
	@PutMapping("/admin/deactivateprofile")
	public boolean deactivateUserProfile(@Valid @RequestBody user_profile user) throws Exception {
		boolean result=false;
		if (userservices.existsById(user.getUser_id())) {
			if (user.getStatus().equalsIgnoreCase("ACTIVE")) {
				System.err.println(user.getStatus());
				user.setStatus(AppConstants.DEACTIVE_USER_STATUS);
				user_profile response =userservices.save(user);
				result=response.getStatus().equals(AppConstants.DEACTIVE_USER_STATUS)?true:false; 
			}
		}
		return result;
	}

	// Get referenceID for new registration
	@GetMapping("/getReferenceId")
	public Optional<user_profile> getInvitedId(@Valid @RequestParam(value="userId") String userId)
			throws Exception, NoSuchElementException {
		if (userservices.existsById(userId)) {
			return userservices.findById(userId);
		}
		return null;
	}

	// get info to recover the user account
	@GetMapping("/getRecoveryInfo")
	@ResponseBody
	public Optional<user_profile> getRecoveryInfo(@Valid @RequestBody user_profile user)
			throws Exception, NoSuchElementException {
		if (userservices.existsById(user.getUser_id())) {
			return userservices.findById(user.getUser_fname());
		}
		return null;
	}

	// Retreive all user info for admin
	@GetMapping("/getAllUserInfo")
	@ResponseBody
	public List<user_profile> getAllUserInfo(@RequestParam(value="role") String role) throws Exception {

		return userservices.getAllUserInfo(role);

	}

	// Search user by Last name
	@GetMapping("/getUserByLname")
	@ResponseBody
	public List<user_profile> getUserByLastName(@RequestParam(value = "lastName") String lastName) throws Exception {

		List<user_profile> profile = userservices.getUserInfo(lastName);
		if (profile != null) {
			return profile;
		}
		return null;
	}

	// Search user by ID
	@GetMapping("/getUserById")
	@ResponseBody
	public Optional<user_profile> getUserById(@RequestParam(value = "userId") String userId)
			throws Exception, NoSuchElementException, UserExceptions {
		Optional<user_profile> profile = userservices.findById(userId);
		if(profile.isPresent()) {
			return profile;
		}
		return profile;
	}

	
	//Lock unlock user account
	@PostMapping("/profilestatus")
	@ResponseBody
	public boolean unLockUserProfile(@RequestParam(value = "userId") String userID,
			@RequestParam(value = "lockStatus") String lockStatus) throws UserExceptions {
		int result = 0;
		if (userservices.existsById(userID)) {
			if (lockStatus.equalsIgnoreCase(AppConstants.IS_LOCKED_LOCKED)) {
				result = userservices.updateProfile(userID, AppConstants.IS_LOCKED_LOCKED);

			} else if (lockStatus.equalsIgnoreCase(AppConstants.IS_LOCKED_CLEAN)) {
				result = userservices.updateProfile(userID, AppConstants.IS_LOCKED_CLEAN);

			}
			if (result > 0) {
				return true;
			}
		}else {
			throw new UserExceptions("Profile not found");
		}
		return false;
	}
	//Validate login
	@PostMapping("/validate")
	
	public void authorizeUser(StubClass creds) {
	if(userservices.existsById(creds.getKey())){
		
		
	}
		
		
	}
}
