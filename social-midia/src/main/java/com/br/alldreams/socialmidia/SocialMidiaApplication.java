package com.br.alldreams.socialmidia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.br.alldreams")
public class SocialMidiaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SocialMidiaApplication.class, args);

		// context.getBean(JobService.class).startService();
	}

}
