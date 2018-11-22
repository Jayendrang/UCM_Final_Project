package library.LibraryAppMailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("library")

public class LibraryAppMailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAppMailServiceApplication.class, args);
	}
}
