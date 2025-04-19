package com.example.mfschain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.mfschain")  // Scan the package where repositories are located
@EntityScan(basePackages = "com.example.mfschain")  // Scan the package where entities are located
public class MfsChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MfsChainApplication.class, args);
    }
}
