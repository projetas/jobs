package com.projetas.car.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.projetas.car.api.config.CarApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CarApiProperty.class)
public class CarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarApiApplication.class, args);
	}
}
