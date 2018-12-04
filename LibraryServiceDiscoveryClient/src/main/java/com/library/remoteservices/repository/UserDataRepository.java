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
import com.library.pojo.user_profile;
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
	private final String totalCount = userRemoteServiceURL.concat("/count");
	private final String countByInstituion = userRemoteServiceURL.concat("/countByInstitution");

	@Override
	public user_profile registerUser(user_profile data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(registerUser);
		return restTemplate.postForObject(builder.build().toUriString(), data, user_profile.class);
	}

	@Override
	public user_profile updateProfile(user_profile data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(updateUserProfile);
		return restTemplate.postForObject(builder.build().toUriString(), data, user_profile.class);
	}

	@Override
	public boolean deactivateProfile(user_profile data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(deactivateProfile);
		return restTemplate.postForObject(builder.build().toUriString(), data, boolean.class);
	}

	@Override
	public user_profile getInviteId(String userId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(referenceId).queryParam("userId", userId);
		return restTemplate.getForObject(builder.build().toUriString(), user_profile.class);
	}

	@Override
	public user_profile getRecoveryQuestions(user_profile data) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(RecoveryInfo);
		return restTemplate.getForObject(builder.build().toUri(), user_profile.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<user_profile> getAllUserDetails(String role) throws NullPointerException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getAllUser).queryParam("role", role);
		List<user_profile> response = null;
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
	public List<user_profile> searchUserInforByLastName(String lastName) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(searchByLastName).queryParam("lastName",
				lastName);
		System.err.println(builder.build().toUri());
		return (List<user_profile>) restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public user_profile getUserById(String userId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getUserById).queryParam("userId", userId);
		System.err.println(builder.toUriString());
		return restTemplate.getForObject(builder.build().toUriString(), user_profile.class);
	}

	@Override
	public boolean lockUnlockUserProfile(String isLocked, String id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(lockUnlockStatus).queryParam("userId", id)
				.queryParam("lockStatus", isLocked);
		System.err.println(builder.toUriString());
		return restTemplate.postForObject(builder.build().toUriString(), null, boolean.class);
	}

	@Override
	public user_profile validateUserCreds(user_profile data) {

		return null;
	}

	@Override
	public StubClass getTotalCount() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(totalCount);
		return restTemplate.getForObject(builder.build().toUriString(), StubClass.class);
	}

	@Override
	public StubClass getUserCountByInstitution(String inst_id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(countByInstituion).queryParam("institution_id",inst_id);
		return restTemplate.getForObject(builder.build().toUriString(), StubClass.class);
	}

}
