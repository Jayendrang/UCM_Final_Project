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
import com.library.remoteservices.repository.InstitutionRepository;
import com.library.remoteservices.repository.LibraryFileRepository;
import com.library.remoteservices.repository.TransactionRepository;
import com.library.remoteservices.repository.UserAuthenticationRepository;
import com.library.remoteservices.repository.UserDataRepository;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient
public class LibraryServiceDiscoveryServerApplication {

	private final String SERVICE_URL="LIBRARY-DAO-SERIVCE";
	private final String FILE_SERVICE = "LIBRARY_FILE_SERVICE";
	private final String MAIL_SERVICE = "LIBRARY-MAIL-SERVICE";
	private final String TEXT_SEARCH ="LIBRARY-TEXT-SEARCH";
	
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
		System.err.println(this.SERVICE_URL+":: User Data repo created");
		return new UserDataRepository();
	}
	
	@Bean
	public BookRepository bookRepo() {
		System.err.println(this.SERVICE_URL+":: Book repo created");
		return new BookRepository();
	}
	
	@Bean
	public LibraryFileRepository fileRepo() {
		System.err.println(this.FILE_SERVICE+"::: File repo created");
		return new LibraryFileRepository(FILE_SERVICE);
	}
	
	@Bean
	public TransactionRepository transRepo() {
		System.err.println(this.SERVICE_URL+"::: Transaction repo created");
		return new TransactionRepository();
	}
	
	@Bean
	public InstitutionRepository institutionRepo() {
		System.err.println(this.SERVICE_URL+"::: Institution repo created");
		return new InstitutionRepository();
	}
	
	@Bean
	public UserAuthenticationRepository userValidator() {
		System.err.println(this.SERVICE_URL+"::: User validator created");
		return new UserAuthenticationRepository();
	}
}
