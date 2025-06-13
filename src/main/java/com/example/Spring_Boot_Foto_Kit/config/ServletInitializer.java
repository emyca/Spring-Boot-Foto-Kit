package com.example.Spring_Boot_Foto_Kit.config;

import com.example.Spring_Boot_Foto_Kit.FotoKitApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FotoKitApplication.class);
	}

}
