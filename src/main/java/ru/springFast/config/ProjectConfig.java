package ru.springFast.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"ru.springFast.services", "ru.springFast.repositories"})
@EnableJpaRepositories("ru.springFast.repositories")
public class ProjectConfig{
}
