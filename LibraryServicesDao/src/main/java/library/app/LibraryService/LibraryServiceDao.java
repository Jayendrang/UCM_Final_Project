package library.app.LibraryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("library.app")
@EntityScan("library.app")
@EnableJpaRepositories("library.app")
@EnableJpaAuditing

public class LibraryServiceDao {

	public static void main(String[] args) {
		
		SpringApplication application  = new SpringApplication(LibraryServiceDao.class);
		application.run(args);
		
	}

}
