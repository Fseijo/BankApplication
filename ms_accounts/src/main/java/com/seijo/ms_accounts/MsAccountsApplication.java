package com.seijo.ms_accounts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@ComponentScans({ @ComponentScan("com.seijo.ms_accounts.controller")})
@EntityScan("com.seijo.ms_accounts.model")
@EnableJpaRepositories("com.seijo.ms_accounts.repositories")
public class MsAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAccountsApplication.class, args);
	}

}
