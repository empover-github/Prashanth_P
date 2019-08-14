package com.ecommerce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EcommerceOrdersAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EcommerceOrdersAppApplication.class, args);
	}

}
