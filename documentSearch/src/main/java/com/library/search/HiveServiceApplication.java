package com.library.search;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HiveServiceApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication appConfs = new SpringApplication(HiveServiceApplication.class);
		appConfs.setDefaultProperties(Collections.singletonMap("server.port","8082"));
		appConfs.run(args);
	}

}
