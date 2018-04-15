package br.com.vehicle.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = { "br.com.vehicle" })
@EnableMongoRepositories(basePackages = { "br.com.vehicle.repository" })
public class VehicleApplicationTests
{

	@Test
	public void contextLoads()
	{}

}
