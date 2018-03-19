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
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.projetas.car.api.model.Car;
import com.projetas.car.api.resource.CarResource;
import com.projetas.car.api.testbuilder.CarBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CarResourceTest {

	@Autowired
	private CarResource carResource;
	
	private Car carTest;
	private LocalDate localDateNow = LocalDate.now();
	
	@Before
	public void carTestData()
	{
		this.carTest = new CarBuilder()
				.withCod((long) 3)
				.withBrand("Hyundai")
				.withModel("HB20")
				.withColor("Cinza")
				.withCreationDate(this.localDateNow)
				.withUpdateDate(null)
				.withNewCar(true)
				.withYear(2018)
				.withPrice(new BigDecimal("32000.00"))
				.build();		
	}
	
	@Test
	public void mustFindTheCarAdded()
	{
		Long cod = this.carTest.getCod();
		int statusCode = 200;
		ResponseEntity<Car> response = carResource.SearchByCode(cod);
		
		assertThat(statusCode,  Matchers.comparesEqualTo(response.getStatusCode().value()));
	}
	
	@Test
	public void mustCreateANewCar()
	{
		int statusCode = 201;
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		ResponseEntity<Car> response = carResource.createCar(this.carTest,httpResponse);
		
		assertThat(statusCode,  Matchers.comparesEqualTo(response.getStatusCode().value()));
	}
	
	@Test
	public void mustUpdateACar()
	{
		int statusCode = 200;
		this.carTest.setColor("Green");
		ResponseEntity<Car> response = carResource.updateCar(this.carTest.getCod(),this.carTest);
		
		assertThat(statusCode,  Matchers.comparesEqualTo(response.getStatusCode().value()));
	}
	
	@Test
	public void mustZDropACar()
	{
		Long cod = this.carTest.getCod();
		carResource.dropCar(cod);

		int statusCode = 404;
		ResponseEntity<Car> response = carResource.SearchByCode(cod);
		
		assertThat(statusCode,  Matchers.comparesEqualTo(response.getStatusCode().value()));
	}

}
