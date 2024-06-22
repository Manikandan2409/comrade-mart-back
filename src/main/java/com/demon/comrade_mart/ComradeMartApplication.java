package com.demon.comrade_mart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ComradeMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComradeMartApplication.class, args);
	}

}
