package com.library.remoteservices.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.pojo.MailDetails;
import com.library.pojo.MailResponse;
import com.library.service.LibraryMailService;

public class MailServiceRepository implements LibraryMailService{

	@Autowired
	RestTemplate restTemplate;

	private final String MAIL_SERVICE = "http://LIBRARY-MAIL-SERVICE".concat("/library").concat("/mailservice");
	private final String sendMailLink = MAIL_SERVICE.concat("/sendmail");
	@Override
	public MailResponse sendMailToLibrarian(MailDetails mailInfo) {
	
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(sendMailLink);
		MailResponse response = restTemplate.postForObject(builder.build().toUriString(), mailInfo, MailResponse.class);
		if(!response.equals(null)) {
			return response;
		}
		return new MailResponse(mailInfo.getUserid(),"failed".toUpperCase());
	}

	

}
