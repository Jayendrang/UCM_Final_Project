package com.library.remoteservices.repository;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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

	

	private final String fileService = discoveryServiceName.concat("/repo").concat("/fileOps");
	private final String setupInstitution = fileService.concat("/setupfolder");
	private final String uploadBooks = fileService.concat("/uploadFile");
	private final String downloadBooks = fileService.concat("/downloadFile");

	@Override
	public boolean setupInstitutionRepository(String institutionName) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(setupInstitution).queryParam("name",
				institutionName);
		return restTemplate.postForObject(builder.build().toUriString(), null, Boolean.class);
	}

	@Override
	public boolean uploadBooks(MultipartFile file, String fileLocation, String fileName) {
		boolean callResponse = false;
		try {
			if(!file.isEmpty()) {
				ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
					public String getFileName() {
						return file.getOriginalFilename();
					}
				};
			LinkedMultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
			//FileSystemResource localResource = new FileSystemResource(file.getResource().getFile().getAbsolutePath());
			requestMap.add("file", fileResource);
			requestMap.add("repoLocation", fileLocation);
			requestMap.add("fileId", fileName);
			
			HttpHeaders requestHeader = new HttpHeaders();
			requestHeader.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
					requestMap, requestHeader);
			System.err.println(requestEntity.getBody());
			System.err.println(uploadBooks);
			ResponseEntity<String> response = restTemplate.exchange(uploadBooks,HttpMethod.POST, requestEntity,
					String.class);
			System.err.println("response from EndPoint---->"+response.getBody());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return callResponse;
	}

	@Override
	public ResponseEntity<Resource> downloadFile(String serverPath, String fileName, HttpServletRequest postRequest) {
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		HttpHeaders responseHeader = new HttpHeaders();

		responseHeader.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> responseEntity = new HttpEntity<>(responseHeader);
		UriComponentsBuilder requestUri = UriComponentsBuilder.fromUriString(downloadBooks)
				.queryParam("repo", serverPath).queryParam("fileId", fileName);
		ResponseEntity<Resource> responseData = restTemplate.exchange(requestUri.build().toUriString(), HttpMethod.GET,
				responseEntity, Resource.class);
		
		return responseData;

	}

}
