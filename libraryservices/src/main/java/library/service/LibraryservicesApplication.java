package library.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryservicesApplication {

	public static void main(String[] args) {
		System.err.println("DOA Application started............!");
		SpringApplication daoService = new SpringApplication(LibraryservicesApplication.class);
		daoService.run(args);
		
	}
}
