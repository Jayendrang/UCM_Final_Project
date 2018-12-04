package library.LibraryFileRepositoryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import library.fileConfig.FileStorageProperty;

@SpringBootApplication
@ComponentScan("library")
@EnableEurekaClient
//@EnableConfigurationProperties({FileStorageProperty.class})
public class LibraryFileRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryFileRepositoryServiceApplication.class, args);
	}
}
