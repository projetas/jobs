package com.projetas.car.api;

import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.projetas.car.api.model.Car;
import com.projetas.car.api.service.CarService;
import com.projetas.car.api.testbuilder.CarBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CarServiceTest {

	@Autowired
	private CarService carService;

	private Car carTest;
	private LocalDate localDateNow = LocalDate.now();
	
	@Before
	public void carTestData()
	{
		this.carTest = new CarBuilder()
				.withCod((long) 1)
				.withBrand("Chevrolet")
				.withModel("Prisma")
				.withColor("Preto")
				.withYear(2017)
				.withDescription("Freio ABS, Seis marchas, Elétrico")
				.withNewCar(false)
				.withCreationDate(this.localDateNow)
				.withUpdateDate(null)
				.withPrice(new BigDecimal("45000.00"))
				.build();		
	}

	@Test
	public void mustUpdateACar()
	{
		Long cod = this.carTest.getCod();
		Car carUpdated = carService.updateCar(cod, this.carTest);
		BigDecimal updatedPrice = new BigDecimal("45000.00"); 
		assertThat(updatedPrice,  Matchers.comparesEqualTo(carUpdated.getPrice()));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void mustFailWhenTryingToUpdateAnInexistCar()
	{
		Long cod = (long) 500000000;
		carService.updateCar(cod, this.carTest);
	}
}
