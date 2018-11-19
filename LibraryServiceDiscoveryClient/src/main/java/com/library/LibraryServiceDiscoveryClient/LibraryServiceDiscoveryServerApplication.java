package com.library.LibraryServiceDiscoveryClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.library.remoteservices.repository.AppPropertiesRepository;
import com.library.remoteservices.repository.BookRepository;
import com.library.remoteservices.repository.LibraryFileRepository;
import com.library.remoteservices.repository.UserDataRepository;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient
public class LibraryServiceDiscoveryServerApplication {

	private final String SERVICE_URL="LIBRARY-DAO-SERIVCE";
	private final String fileService = "LIBRARY_FILE_SERVICE";
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceDiscoveryServerApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public AppPropertiesRepository propertyRepo() {
		System.err.println(this.SERVICE_URL);
		return new AppPropertiesRepository();
	}
	
	@Bean
	public UserDataRepository userRepo() {
		System.err.println(this.SERVICE_URL+":: User Data Repo");
		return new UserDataRepository();
	}
	
	@Bean
	public BookRepository bookRepo() {
		System.err.println(this.SERVICE_URL+":: Book Repo");
		return new BookRepository();
	}
	
	@Bean
	public LibraryFileRepository fileRepo() {
		System.err.println(this.fileService+"::: File repo");
		return new LibraryFileRepository(fileService);
	}
}
