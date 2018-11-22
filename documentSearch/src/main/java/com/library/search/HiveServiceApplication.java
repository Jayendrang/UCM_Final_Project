package com.library.search;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.library.search")
@EnableConfigurationProperties({DBConnectionProperties.class})
public class HiveServiceApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication appConfs = new SpringApplication(HiveServiceApplication.class);
		appConfs.run(args);
	}

}
