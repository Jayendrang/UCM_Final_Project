package com.library.service;

import java.util.List;

import com.library.pojo.StubClass;
import com.library.pojo.user_profile;

public interface UserSerivces {

	public user_profile registerUser(user_profile data);

	public user_profile updateProfile(user_profile data);

	public boolean deactivateProfile(user_profile data);

	public boolean lockUnlockUserProfile(String isLocked, String id);

	public user_profile getInviteId(String userId);

	public user_profile getRecoveryQuestions(user_profile data);

	public List<user_profile> getAllUserDetails(String role);

	public List<user_profile> searchUserInforByLastName(String lastName);

	public user_profile getUserById(String userId);

	public user_profile validateUserCreds(user_profile data);

	public StubClass getTotalCount();

	public StubClass getUserCountByInstitution(String inst_id);

}
