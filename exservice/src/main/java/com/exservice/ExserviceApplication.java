package com.exservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ExserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExserviceApplication.class, args);
	}
}
