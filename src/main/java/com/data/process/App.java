package com.data.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.data.*")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
