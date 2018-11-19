package com.library.LibraryServiceDiscoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.service.BookServices;

@RestController
@RequestMapping("/library/book")
public class BookServiceController {

	@Autowired
	BookServices bookservices;
	
	//@PostMapping("/saveBook")
	
	
}
