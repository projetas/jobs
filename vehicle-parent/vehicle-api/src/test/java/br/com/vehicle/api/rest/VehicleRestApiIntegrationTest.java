package br.com.vehicle.api.rest;

import static br.com.vehicle.support.handler.JsonHandler.toJson;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.vehicle.api.VehicleApplication;
import br.com.vehicle.model.domain.Vehicle;
import br.com.vehicle.model.domain.VehicleBrand;
import br.com.vehicle.model.domain.VehicleColor;
import br.com.vehicle.model.domain.VehicleType;
import br.com.vehicle.repository.VehicleRepository;
import br.com.vehicle.support.exception.AppCode;
import br.com.vehicle.support.handler.I18nHandler;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = VehicleApplication.class)
@AutoConfigureMockMvc
@EnableMongoRepositories(basePackages = { "br.com.vehicle.repository" })
@TestPropertySource(locations = "classpath:application-integration.properties")
public class VehicleRestApiIntegrationTest
{
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mvc;

	@Autowired
	private I18nHandler i18n;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Before
	public void setup()
	{
		vehicleRepository.deleteAll();
	}

	@Test
	public void insertBrandStatus201Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		String message = i18n.getMessage(AppCode.MSG_INSERT_SUCCESS, Locale.getDefault(), vehicle.getModel());
		mvc.perform(post("/vehicle/api/insert")
				.content(toJson(vehicle)).contentType(contentType))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Assert.assertEquals(Boolean.TRUE, vehicleRepository.findOne(Example.of(vehicle)).isPresent());
	}

	private Vehicle newVehicle()
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(VehicleBrand.builder().brand("FORD").build());
		vehicle.setModel("New Fiesta");
		vehicle.setColor(VehicleColor.builder().color("BRANCA").build());
		vehicle.setType(VehicleType.CAR);
		vehicle.setDescription("ABS, AirBag, Blutooth");
		vehicle.setPrice(56000D);
		vehicle.setUnused(true);
		vehicle.setYear(2018);
		return vehicle;
	}
}
