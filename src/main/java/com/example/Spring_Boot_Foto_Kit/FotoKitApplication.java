package com.example.Spring_Boot_Foto_Kit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FotoKitApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FotoKitApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FotoKitApplication.class);
	}

}
