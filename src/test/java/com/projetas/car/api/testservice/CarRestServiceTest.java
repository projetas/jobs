package com.projetas.car.api.testservice;

import static com.jayway.restassured.RestAssured.given;

import org.springframework.stereotype.Service;

import com.jayway.restassured.path.json.JsonPath;
import com.projetas.car.api.model.Car;

@Service
public class CarRestServiceTest {

	public Car addNewCar(Car carTest)
	{
		JsonPath jsonResponse = 
			given()
				.header("Accept","application/json")
				.contentType("application/json")
				.body(carTest)
			.when()
				.post("/cars")
			.andReturn()
				.jsonPath();
		
		return jsonResponse.getObject("",Car.class);
	}
	
	public Car updateCar(Car carTest)
	{
		String updatePath = "/cars/" + carTest.getCod();
		
		JsonPath jsonResponse = 
			given()
				.header("Accept","application/json")
				.contentType("application/json")
				.body(carTest)
			.when()
				.put(updatePath)
			.andReturn()
				.jsonPath();
		
		return jsonResponse.getObject("",Car.class);
	}
	
	public void updateNewCarProperty(Car carTest, int statusCode)
	{
		String updatePath = "/cars/" + carTest.getCod() + "/newcar";
		
		given()
			.header("Accept","application/json")
			.contentType("application/json")
			.body(carTest.getNewCar())
		.when()
			.put(updatePath)
		.then()
			.statusCode(statusCode);
	}
	
	public void checkStatusCodeForANewCar(Car carTest, int statusCode)
	{
		given()
			.header("Accept","application/json")
			.contentType("application/json")
			.body(carTest)
		.when()
			.post("/cars")
		.then()
			.statusCode(statusCode);
	}

	public void checkStatusCodeForUpdatingACar(Car carTest, int statusCode)
	{
		String updatePath = "/cars/" + carTest.getCod();
		
		given()
			.header("Accept","application/json")
			.contentType("application/json")
			.body(carTest)
		.when()
			.put(updatePath)
		.then()
			.statusCode(statusCode);
	}

	public void deleteCar(Long cod, int statusCode)
	{
		String deletePath = "/cars/" + cod;  

		given()
			.contentType("application/json")
		.when()
			.delete(deletePath)
		.then()
			.statusCode(statusCode);
	}
	
	public int totalCarsAdded()
	{
		JsonPath jsonResponse = 
				given()
					.header("Accept","application/json")
					.get("/cars")
				.andReturn()
					.jsonPath();

		return jsonResponse.getInt("totalElements");
	}

	public void checkStatusCodeForGettingACar(long cod, int statusCode) 
	{
		String getPath = "/cars/" + cod;  

		given()
			.contentType("application/json")
		.when()
			.get(getPath)
		.then()
			.statusCode(statusCode);
	}
}
