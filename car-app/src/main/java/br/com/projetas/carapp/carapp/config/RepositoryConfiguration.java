package br.com.projetas.carapp.carapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"br.com.projetas.carapp.carapp.entity"})
@EnableJpaRepositories(basePackages = {"br.com.projetas.carapp.carapp.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
