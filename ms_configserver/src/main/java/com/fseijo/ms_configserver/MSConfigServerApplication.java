package com.fseijo.ms_configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MSConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSConfigServerApplication.class, args);
    }
}