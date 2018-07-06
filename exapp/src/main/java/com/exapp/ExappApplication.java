package com.exapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller
@ComponentScan(basePackages = {"com.exservice","com.exapp"})
@SpringBootApplication
public class ExappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExappApplication.class, args);
	}
}
