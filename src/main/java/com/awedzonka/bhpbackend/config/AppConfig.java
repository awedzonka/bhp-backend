package com.awedzonka.bhpbackend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan("com.awedzonka")
@EnableTransactionManagement
@EnableJpaRepositories("com.awedzonka.bhpbackend.repository")
@EntityScan(basePackages = "com.awedzonka.bhpbackend.model")
public class AppConfig {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
