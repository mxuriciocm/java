package com.acme.pc21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Pc21Application {

    public static void main(String[] args) {
        SpringApplication.run(Pc21Application.class, args);
    }

}
