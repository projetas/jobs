package com.carservice.carServiceDocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EnableAutoConfiguration
public class CarServiceDockerApplication {

	public static void main(String[] args) {

		run(CarServiceDockerApplication.class, args);
	}
}
