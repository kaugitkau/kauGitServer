package com.example.Kau_Git;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KauGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(KauGitApplication.class, args);
	}

}
