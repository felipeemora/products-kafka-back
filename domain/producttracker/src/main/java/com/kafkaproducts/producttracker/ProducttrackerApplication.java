package com.kafkaproducts.producttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProducttrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducttrackerApplication.class, args);
	}

}
