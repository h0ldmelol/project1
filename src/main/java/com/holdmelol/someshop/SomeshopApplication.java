package com.holdmelol.someshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SomeshopApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SomeshopApplication.class, args);
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		System.out.println(encoder.encode("admin"));
	}

}
