package br.com.vehicle.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = { "br.com.vehicle" })
@EnableMongoRepositories(basePackages = { "br.com.vehicle.repository" })
public class VehicleApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(VehicleApplication.class, args);
	}
}
