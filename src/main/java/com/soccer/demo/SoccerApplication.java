package com.soccer.demo;

import com.soccer.demo.models.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SoccerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerApplication.class, args);
	}

}
