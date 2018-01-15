package br.com.projetas.vehiclerestwebapp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.projetas.vehiclerestwebapp.model.Vehicle;
import br.com.projetas.vehiclerestwebapp.repository.VehicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VehicleControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private VehicleRepository vehicleRepository;

	// an alternative form to restTemplate tests
	@Autowired
	private MockMvc mockMvc;
	
	private Vehicle vehicle1;
	private Vehicle vehicle2;

	@TestConfiguration
	static class Config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthorization("admin", "admin");
		}
	}
	
	@Before
	public void setup() {
		vehicle1 = new Vehicle(1L, "Tesla", "Model 3", "black", 2018, 35000, 
				"A brand new Model 3", true, 
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		vehicle2 = new Vehicle(2L, "Tesla Motors", "Model X", "silver", 2018, 100000, 
				"A brand new Model X", false, 
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		BDDMockito.when(vehicleRepository.findOne(vehicle1.getId())).thenReturn(vehicle1);
		BDDMockito.when(vehicleRepository.findOne(vehicle2.getId())).thenReturn(vehicle2);
	}
	
	@Test
	public void getAllVehiclesWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
		restTemplate = restTemplate.withBasicAuth("1", "1");
		ResponseEntity<String> response = restTemplate.getForEntity("/user/vehicles", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void getVehicleByIdWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
		restTemplate = restTemplate.withBasicAuth("1", "1");
		ResponseEntity<String> response = restTemplate.getForEntity("/user/vehicles/{id}", String.class, vehicle1.getId());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void getAllVehiclesWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
		List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);
		BDDMockito.when(vehicleRepository.findAll()).thenReturn(vehicles);
		ResponseEntity<String> response = restTemplate.getForEntity("/user/vehicles/", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void getVehicleByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
		ResponseEntity<Vehicle> response = restTemplate.getForEntity("/user/vehicles/{id}", Vehicle.class, vehicle1.getId());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void getVehicleByIdWhenUsernameAndPasswordAreCorrectAndVehicleDoesNotExistShouldReturnStatusCode404() {
		ResponseEntity<Vehicle> response = restTemplate.getForEntity("/user/vehicles/{id}", Vehicle.class, -1);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void deleteWhenUserHasRoleAdminAndVehicleExistsShouldReturnStatusCode200() {
		BDDMockito.doNothing().when(vehicleRepository).delete(1L);
		ResponseEntity<String> exchange = restTemplate.exchange("/admin/vehicles/{id}", HttpMethod.DELETE, null, String.class, 1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void deleteWhenUserHasRoleAdminAndVehicleDoesNotExistShouldReturnStatusCode404() {
		BDDMockito.doNothing().when(vehicleRepository).delete(-1L);
		ResponseEntity<String> exchange = restTemplate.exchange("/admin/vehicles/{id}", HttpMethod.DELETE, null, String.class, -1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
	}
	
	// Testing with Mock, so that the ROLE can be changed to perform the test
	@Test
	@WithMockUser(username = "xx", password = "xx", roles = {"USER"})
	public void deleteWhenUserDoesNotHaveRoleAdminShouldReturnStatusCode403() throws Exception {
		BDDMockito.doNothing().when(vehicleRepository).delete(1L);
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/admin/vehicles/{id}", 1L))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}
	
	@Test
	public void createWhenBrandIsNullShouldReturnStatusCode400() throws Exception {
		Vehicle vehicle = new Vehicle(1L, null, "Model 3", "black", 2018, 35000, 
					"A brand new Model 3", true, 
					new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		BDDMockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
		ResponseEntity<String> response = restTemplate.postForEntity("/admin/vehicles/", vehicle, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		Assertions.assertThat(response.getBody()).contains("fieldMessage", "Brand can not be null");
	}
	
	@Test
	public void createWhenModelIsNullShouldReturnStatusCode400() throws Exception {
		Vehicle vehicle = new Vehicle(1L, "Tesla", null, "black", 2018, 35000, 
					"A brand new Model 3", true, 
					new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		BDDMockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
		ResponseEntity<String> response = restTemplate.postForEntity("/admin/vehicles/", vehicle, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		Assertions.assertThat(response.getBody()).contains("fieldMessage", "Model can not be null");
	}
	
	@Test
	public void createWhenYearLessThanZeroShouldReturnStatusCode400() throws Exception {
		Vehicle vehicle = new Vehicle(1L, "Tesla", "Model 3", "black", -1, 35000, 
					"A brand new Model 3", true, 
					new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		BDDMockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
		ResponseEntity<String> response = restTemplate.postForEntity("/admin/vehicles/", vehicle, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		Assertions.assertThat(response.getBody()).contains("fieldMessage", "Year must be a positive number");
	}
	
	@Test
	public void createShouldPersistDataAndReturnStatusCode201() throws Exception {
		BDDMockito.when(vehicleRepository.save(vehicle1)).thenReturn(vehicle1);
		ResponseEntity<Vehicle> response = restTemplate.postForEntity("/admin/vehicles", vehicle1, Vehicle.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}
}
