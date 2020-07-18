package com.murta.stoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class StoomApplication  extends SpringBootServletInitializer {



	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StoomApplication.class);
	}


	public static void main(String[] args) {

		System.out.println("Iniciando a app...");
		SpringApplication.run(StoomApplication.class, args);
		System.out.println("App iniciada...");
	}

}
