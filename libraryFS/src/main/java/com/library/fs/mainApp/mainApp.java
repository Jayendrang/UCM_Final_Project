package com.library.fs.mainApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.library.fs.beans.FileStorageProperties;

@SpringBootApplication
@ComponentScan("com.library")
@EnableConfigurationProperties({FileStorageProperties.class})
public class mainApp {
	
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(mainApp.class);
	    }
	 public static void main(String[] args) {
		 SpringApplication.run(mainApp.class, args);
	 }
	 
	 
	
	
}
