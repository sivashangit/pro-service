package com.siva.springproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringproductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringproductApplication.class, args);
	}

}
