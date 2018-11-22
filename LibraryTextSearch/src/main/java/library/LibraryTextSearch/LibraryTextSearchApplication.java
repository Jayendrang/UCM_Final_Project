package library.LibraryTextSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import library.pojo.HiveConnectionProperties;

@Configuration
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("library")
public class LibraryTextSearchApplication {
	
	@Autowired
	private HiveConnectionProperties connProperties;

	public static void main(String[] args) {
		SpringApplication.run(LibraryTextSearchApplication.class, args);
	}
	

	
}
