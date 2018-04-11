package br.com.vehicle.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.vehicle" })
public class VehicleApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(VehicleApplication.class, args);
	}
}
