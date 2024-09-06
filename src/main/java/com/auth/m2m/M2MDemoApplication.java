package com.auth.m2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class M2MDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(M2MDemoApplication.class, args);
	}

}
