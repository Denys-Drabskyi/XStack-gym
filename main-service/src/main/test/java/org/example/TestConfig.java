package org.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Primary
@Configuration
@EnableJpaRepositories(basePackages = "org.example.repository")
public class TestConfig {
}
