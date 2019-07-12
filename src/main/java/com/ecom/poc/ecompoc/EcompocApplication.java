package com.ecom.poc.ecompoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EcompocApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		// return super.configure(builder);
		return builder.sources(EcompocApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EcompocApplication.class, args);
	}

}
