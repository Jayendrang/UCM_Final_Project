package com.library.remoteservices.repository;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.service.LibraryFileRepoService;

public class LibraryFileRepository implements LibraryFileRepoService {
	@Autowired
	RestTemplate restTemplate;
	
	private final String discoveryServiceName = "http://LIBRARY-FILE-SERVICE";
	
	public LibraryFileRepository(String serviceName) {
		System.err.println("Library Remote File service created");
	}

	

	private final String fileService = discoveryServiceName.concat("/repo").concat("/cloud/");
	private final String setupInstitution = fileService.concat("create");
	private final String uploadBooks = fileService.concat("upload");
	private final String removeBooks = fileService.concat("remove");

	@Override
	public boolean setupInstitutionRepository(String institutionName) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(setupInstitution).queryParam("name",
				institutionName);
		return restTemplate.postForObject(builder.build().toUriString(), null, boolean.class);
	}

	@Override
	public boolean uploadBooks(MultipartFile file, String fileLocation, String fileName) {
		boolean callResponse = false;
		try {
			if(!file.isEmpty()) {
			org.springframework.util.MultiValueMap<String,Object> requestMap = new LinkedMultiValueMap<String,Object>();
			requestMap.add("file", file.getResource());
			requestMap.add("path", fileLocation);
			requestMap.add("name", fileName);
			
			HttpHeaders requestHeader = new HttpHeaders();
			requestHeader.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<org.springframework.util.MultiValueMap<String, Object>> requestEntity = new HttpEntity<org.springframework.util.MultiValueMap<String, Object>>(
					requestMap, requestHeader);
			System.err.println(requestEntity.getBody());
			System.err.println(uploadBooks);
			callResponse = restTemplate.postForObject(uploadBooks, requestEntity, boolean.class);
			//(uploadBooks,HttpMethod.POST, requestEntity,String.class);
			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return callResponse;
	}

	
	//removed and configured to CDN
	@Override
	public ResponseEntity<Resource> downloadFile(String serverPath, String fileName, HttpServletRequest postRequest) {
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		HttpHeaders responseHeader = new HttpHeaders();

		responseHeader.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> responseEntity = new HttpEntity<>(responseHeader);
		UriComponentsBuilder requestUri = UriComponentsBuilder.fromUriString(removeBooks)
				.queryParam("repo", serverPath).queryParam("fileId", fileName);
		ResponseEntity<Resource> responseData = restTemplate.exchange(requestUri.build().toUriString(), HttpMethod.GET,
				responseEntity, Resource.class);
		
		return responseData;

	}

	@Override
	public boolean removeBook(String path, String fileName) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(removeBooks).queryParam("path",path).queryParam("name",fileName);
		return restTemplate.postForObject(builder.build().toUriString(), null, boolean.class);
	}
}
