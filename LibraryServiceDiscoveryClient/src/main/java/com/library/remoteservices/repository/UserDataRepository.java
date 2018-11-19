package com.library.remoteservices.repository;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponents.UriTemplateVariables;

import com.github.andrewoma.dexx.collection.Map;
import com.google.common.collect.Lists;
import com.library.pojo.StubClass;
import com.library.pojo.User_Info;
import com.library.service.UserSerivces;

public class UserDataRepository implements UserSerivces {

	@Autowired
	private RestTemplate restTemplate;
	protected final String userRemoteServiceURL = "http://LIBRARY-DAO-SERIVCE".concat("/library").concat("/user");

	private final String registerUser = userRemoteServiceURL.concat("/register");
	private final String updateUserProfile = userRemoteServiceURL.concat("/updateuserprofile");
	private final String deactivateProfile = userRemoteServiceURL.concat("/deactivateprofile");
	private final String referenceId = userRemoteServiceURL.concat("/getReferenceId/");
	private final String RecoveryInfo = userRemoteServiceURL.concat("/getRecoveryInfo/");
	private final String getAllUser = userRemoteServiceURL.concat("/getAllUserInfo/");
	private final String searchByLastName = userRemoteServiceURL.concat("/getUserByLname");
	private final String getUserById = userRemoteServiceURL.concat("/getUserById/");
	private final String lockUnlockStatus = userRemoteServiceURL.concat("/profilestatus");

	@Override
	public User_Info registerUser(User_Info data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(registerUser);
		return restTemplate.postForObject(builder.build().toUriString(), data, User_Info.class);
	}

	@Override
	public User_Info updateProfile(User_Info data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(updateUserProfile);
		return restTemplate.postForObject(builder.build().toUriString(), data, User_Info.class);
	}

	@Override
	public boolean deactivateProfile(User_Info data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(deactivateProfile);
		return restTemplate.postForObject(builder.build().toUriString(), data, boolean.class);
	}

	@Override
	public User_Info getInviteId(String userId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(referenceId).queryParam("userId",userId);
		return restTemplate.getForObject(builder.build().toUriString(), User_Info.class);
	}

	@Override
	public User_Info getRecoveryQuestions(User_Info data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(RecoveryInfo);
		return restTemplate.getForObject(builder.build().toUri(), User_Info.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User_Info> getAllUserDetails(String role) throws NullPointerException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getAllUser).queryParam("role",role);
		List<User_Info> response = null;
		try {
			System.out.println(getAllUser);
			response = restTemplate.getForObject(builder.build().toUriString(), List.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User_Info> searchUserInforByLastName(String lastName) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(searchByLastName).queryParam("lastName", lastName);
		System.err.println(builder.build().toUri());
		return (List<User_Info>) restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public User_Info getUserById(String userId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getUserById).queryParam("userId", userId);
		System.err.println(builder.toUriString());
		return restTemplate.getForObject(builder.build().toUriString(), User_Info.class);
	}

	@Override
	public boolean lockUnlockUserProfile(String isLocked, String id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(lockUnlockStatus)
				.queryParam("userId", id)
				.queryParam("lockStatus",isLocked);
		System.err.println(builder.toUriString());
		return restTemplate.postForObject(builder.build().toUriString(),null,boolean.class);
	}

	@Override
	public User_Info validateUserCreds(User_Info data) {

		return null;
	}

}
