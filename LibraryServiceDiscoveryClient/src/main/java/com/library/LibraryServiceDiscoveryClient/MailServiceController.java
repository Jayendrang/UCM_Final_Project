package com.library.LibraryServiceDiscoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.MailDetails;
import com.library.pojo.MailResponse;
import com.library.service.LibraryMailService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/library/mail")
public class MailServiceController {

	@Autowired
	LibraryMailService mailService;
	
	@PostMapping("/send")
	public MailResponse sendMail(@RequestBody MailDetails details) {
		return mailService.sendMailToLibrarian(details);
	}
	
}
