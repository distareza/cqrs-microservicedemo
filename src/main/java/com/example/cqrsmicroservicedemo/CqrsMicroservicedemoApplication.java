package com.example.cqrsmicroservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CqrsMicroservicedemoApplication {

	@RequestMapping("/")
	public String rootPath() {
		return "this is example of MyMusic API";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CqrsMicroservicedemoApplication.class, args);
	}

}
