package com.fullstackmarkdownbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {FlywayAutoConfiguration.class})
public class FullStackMarkDownBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullStackMarkDownBackEndApplication.class, args);
    }

}
