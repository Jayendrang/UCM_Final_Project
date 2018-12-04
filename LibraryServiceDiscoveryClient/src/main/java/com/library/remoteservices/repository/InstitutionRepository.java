package com.library.remoteservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.pojo.InstitutionInfo;
import com.library.pojo.StubClass;
import com.library.service.InstitutionService;

public class InstitutionRepository implements InstitutionService {

	@Autowired
	RestTemplate restTemplate;

	protected final String institutionRemoteServiceURL = "http://LIBRARY-DAO-SERIVCE".concat("/library")
			.concat("/school");
	private final String getInstitutionInfo = institutionRemoteServiceURL.concat("/getInfo");
	private final String getAllInstitutionInfo = institutionRemoteServiceURL.concat("/getAllInfo");
	private final String saveInstitution = institutionRemoteServiceURL.concat("/saveInstitution");
	private final String updateStatus = institutionRemoteServiceURL.concat("/statusupdate");
	private final String totalCount = institutionRemoteServiceURL.concat("/count");

	@Override
	public InstitutionInfo getInstitutionInfo(String id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getInstitutionInfo)
				.queryParam("institution_id", id);

		Optional<InstitutionInfo> response = restTemplate.getForObject(builder.build().toUriString(), Optional.class);
		return response.get();
	}

	@Override
	public List<InstitutionInfo> getAllInstitutionInfo() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getAllInstitutionInfo);
		List<InstitutionInfo> response = restTemplate.getForObject(builder.build().toUriString(), List.class);
		return response;
	}

	@Override
	public InstitutionInfo saveNewInstitution(InstitutionInfo info) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(saveInstitution).queryParam("adminId",
				info.getCreated_by());
		return restTemplate.postForObject(builder.build().toUriString(), info, InstitutionInfo.class);
	}

	@Override
	public boolean updateInstitutionStatus(String message) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(updateStatus);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(message, headers);
		return restTemplate.postForObject(builder.build().toUriString(), entity, Boolean.class);
	}

	@Override
	public InstitutionInfo updateInstitutionProfile(InstitutionInfo info) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(updateStatus);
		return restTemplate.postForObject(builder.build().toUriString(), info, InstitutionInfo.class);
	}

	@Override
	public StubClass getTotalCount() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(totalCount);
		return restTemplate.getForObject(builder.build().toUriString(), StubClass.class);
	}

}
