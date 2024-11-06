package com.ono.diego.spring.milagroman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MilagromanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilagromanApplication.class, args);
		System.out.println("Appication started successfully on port 8080");
	}
}
