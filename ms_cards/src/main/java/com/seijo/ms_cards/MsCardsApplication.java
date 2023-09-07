package com.seijo.ms_cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@ComponentScans({ @ComponentScan("com.seijo.ms_cards.controller")})
@EntityScan("com.seijo.ms_cards.model")
@EnableJpaRepositories("com.seijo.ms_cards.repositories")
public class MsCardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCardsApplication.class, args);
	}

}
