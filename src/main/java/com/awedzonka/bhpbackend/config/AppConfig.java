package com.awedzonka.bhpbackend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("com.awedzonka")
@EnableTransactionManagement
@EnableJpaRepositories("com.awedzonka.bhpbackend.repository")
@EntityScan(basePackages = "com.awedzonka.bhpbackend.model")
public class AppConfig {

}
