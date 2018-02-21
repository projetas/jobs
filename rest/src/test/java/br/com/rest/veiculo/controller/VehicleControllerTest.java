package br.com.rest.veiculo.controller;

import java.util.Date;

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

import br.com.rest.veiculo.entity.Veiculo;
import br.com.rest.veiculo.repository.VeiculoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VehicleControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VeiculoRepository vehicleRepository;

	private Veiculo vehicle1;
	private Veiculo vehicle2;

	@TestConfiguration
	static class Config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthorization("admin", "admin");
		}
	}

	@Before
	public void setup() {
		vehicle1 = new Veiculo(1L, "GOL", "Model teste", "red", 2018, 10000, "Description 1", true,
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		vehicle2 = new Veiculo(2L, "GOL WOLKS", "Model 2", "silver", 2018, 100000,
"A brand new Model 2", false,
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		BDDMockito.when(vehicleRepository.findOne(vehicle1.getId())).thenReturn(vehicle1);
		BDDMockito.when(vehicleRepository.findOne(vehicle2.getId())).thenReturn(vehicle2);
	}

	@Test
	public void getVehicleByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
		ResponseEntity<Veiculo> response = restTemplate.getForEntity("/user/vehicles/{cod}", Veiculo.class, vehicle1.getId());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void deleteWhenUserHasRoleAdminAndVehicleExistsShouldReturnStatusCode200() {
		BDDMockito.doNothing().when(vehicleRepository).delete(1L);
		ResponseEntity<String> exchange = restTemplate.exchange("/admin/vehicles/{cod}", HttpMethod.DELETE, null, String.class, 1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void deleteWhenUserHasRoleAdminAndVehicleDoesNotExistShouldReturnStatusCode404() {
		BDDMockito.doNothing().when(vehicleRepository).delete(-1L);
		ResponseEntity<String> exchange = restTemplate.exchange("/admin/vehicles/{cod}", HttpMethod.DELETE, null, String.class, -1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void getAllVehiclesWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
		restTemplate = restTemplate.withBasicAuth("1", "1");
		ResponseEntity<String> response = restTemplate.getForEntity("/user/vehicles", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
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
		Veiculo vehicle = new Veiculo(1L, null, "Model teste", "red", 2018, 10000,
					"Description 1", true,
					new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		BDDMockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
		ResponseEntity<String> response = restTemplate.postForEntity("/admin/vehicles/", vehicle, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		Assertions.assertThat(response.getBody()).contains("fieldMessage", "Required field brand");
	}

	@Test
	public void createWhenYearLessThanZeroShouldReturnStatusCode400() throws Exception {
		Veiculo vehicle = new Veiculo(1L, "GOL", "Model teste", "red", -1, 10000,
					"Description 1", true,
					new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		BDDMockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
		ResponseEntity<String> response = restTemplate.postForEntity("/admin/vehicles/", vehicle, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		Assertions.assertThat(response.getBody()).contains("fieldMessage", "invalid Year");
	}

	@Test
	public void createShouldPersistDataAndReturnStatusCode201() throws Exception {
		BDDMockito.when(vehicleRepository.save(vehicle1)).thenReturn(vehicle1);
		ResponseEntity<Veiculo> response = restTemplate.postForEntity("/admin/vehicles", vehicle1, Veiculo.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}
}
