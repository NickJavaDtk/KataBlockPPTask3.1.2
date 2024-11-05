package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.domain.model.User;

@Configuration
public class BeenConfig {
    @Bean
    public User user() {
        return new User();
    }
}
