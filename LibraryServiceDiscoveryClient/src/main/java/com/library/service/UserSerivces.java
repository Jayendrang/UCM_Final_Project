package com.library.service;

import java.util.List;

import com.library.pojo.User_Info;

public interface UserSerivces {
	
	public User_Info registerUser(User_Info data);
	public User_Info updateProfile(User_Info data);
	public boolean deactivateProfile(User_Info data);
	public boolean lockUnlockUserProfile(String isLocked,String id);
	public User_Info getInviteId(String userId);
	public User_Info getRecoveryQuestions(User_Info data);
	public List<User_Info> getAllUserDetails(String role);
	public List<User_Info> searchUserInforByLastName(String lastName);
	public User_Info getUserById(String userId);
	public User_Info validateUserCreds(User_Info data);
	
}
