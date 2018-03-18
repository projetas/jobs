package com.projetas.car.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.projetas.car.api.model.Car;
import com.projetas.car.api.testbuilder.CarBuilder;
import com.projetas.car.api.testservice.CarRestServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CarRestTests {

	@Autowired
	private CarRestServiceTest carRestServiceTest;
	
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
	public void mustCreateACar()
	{
		Car carTestCreated = carRestServiceTest.addNewCar(this.carTest);
		
		assertEquals("Hyundai",carTestCreated.getBrand());
		assertEquals("HB20",carTestCreated.getModel());
		assertEquals("Cinza",carTestCreated.getColor());
		assertEquals(2018,carTestCreated.getYear(),0.0001);
		assertEquals(this.localDateNow,carTestCreated.getCreationDate());
		assertEquals(null,carTestCreated.getUpdateDate());
		assertTrue(carTestCreated.getNewCar());
	}
	
	@Test
	public void mustReceiveThreeCars()
	{
		int expectedTotalElements = 3;
		int totalElements = carRestServiceTest.totalCarsAdded();
		assertEquals(expectedTotalElements,totalElements);
	}
	
	@Test
	public void mustUpdateTheCarColor()
	{
		Car carTestCreated = this.carTest;
		carTestCreated.setColor("Brown");
		
		Car carTestUpdated = carRestServiceTest.updateCar(carTestCreated);
		assertEquals("Brown",carTestUpdated.getColor());
	}
	
	@Test
	public void mustUpdateNewCarStatusFromACar()
	{
		Car carTestCreated = this.carTest;
		carTestCreated.setNewCar(false);

		int statusCode = 204;
		carRestServiceTest.updateNewCarProperty(this.carTest, statusCode);
	}

	@Test
	public void mustZDeleteTheCarAdded()
	{
		int statusCode = 204;
		carRestServiceTest.deleteCar(carTest.getCod(), statusCode);
		
		int expectedTotalElements = 2;
		int totalElements = carRestServiceTest.totalCarsAdded();
		assertEquals(expectedTotalElements,totalElements);
	}
	
	@Test
	public void mustReturnStatusNotFoundWhenTryingToGetAnInexistingCar()
	{
		int statusCode = 404;
		carRestServiceTest.checkStatusCodeForGettingACar((long) 500000000, statusCode);
	}

	@Test
	public void mustReturnStatusNotFoundWhenTryingToDeleteAnInexistingCar()
	{
		int statusCode = 404;
		carRestServiceTest.deleteCar((long) 500000000, statusCode);
	}

	@Test
	public void mustReturnStatusNotFoundWhenTryingToUpdateAnInexistingCar()
	{
		int statusCode = 404;
		Long cod = (long) 500000000; 
		this.carTest.setCod(cod);
		carRestServiceTest.checkStatusCodeForUpdatingACar(this.carTest, statusCode);
	}

	@Test
	public void mustReturnStatusNotFoundWhenTryingToUpdateNewCarStatusFromACar()
	{
		int statusCode = 404;
		Long cod = (long) 500000000; 
		this.carTest.setCod(cod);
		carRestServiceTest.updateNewCarProperty(this.carTest, statusCode);
	}

	@Test
	public void mustReturnBadRequestWhenTheBrandSizeIsBiggerThanFourtyCharacters()
	{
		int statusCode = 400;
		this.carTest.setBrand("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
		carRestServiceTest.checkStatusCodeForANewCar(this.carTest,statusCode);
	}

	@Test
	public void mustReturnBadRequestWhenTheModelSizeIsBiggerThanFiftyCharacters()
	{
		int statusCode = 400;
		this.carTest.setModel("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
		carRestServiceTest.checkStatusCodeForANewCar(this.carTest,statusCode);
	}

	@Test
	public void mustReturnBadRequestWhenTheColorSizeIsBiggerThanThirtyCharacters()
	{
		int statusCode = 400;
		this.carTest.setColor("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
		carRestServiceTest.checkStatusCodeForANewCar(this.carTest,statusCode);
	}

	@Test
	public void mustReturnBadRequestWhenTheCreationDateIsNull()
	{
		int statusCode = 400;
		this.carTest.setCreationDate(null);
		carRestServiceTest.checkStatusCodeForANewCar(this.carTest,statusCode);
	}

	@Test
	public void mustReturnBadRequestWhenTheFlagNewCarIsNull()
	{
		int statusCode = 400;
		this.carTest.setNewCar(null);
		carRestServiceTest.checkStatusCodeForANewCar(this.carTest,statusCode);
	}

	@Test
	public void mustReturnBadRequestWhenAnInvalidYearWasSend()
	{
		int statusCode = 400;
		this.carTest.setYear(-1000);
		carRestServiceTest.checkStatusCodeForANewCar(this.carTest,statusCode);
	}

	@Test
	public void mustReturnBadRequestWhenAnInvalidPriceWasSend()
	{
		int statusCode = 400;
		this.carTest.setPrice(new BigDecimal("-32000.00"));
		carRestServiceTest.checkStatusCodeForANewCar(this.carTest,statusCode);
	}

}
