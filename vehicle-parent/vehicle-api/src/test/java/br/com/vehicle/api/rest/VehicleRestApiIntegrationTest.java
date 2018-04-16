package br.com.vehicle.api.rest;

import static br.com.vehicle.support.exception.AppCode.MSG_DELETE_SUCCESS;
import static br.com.vehicle.support.exception.AppCode.MSG_INSERT_SUCCESS;
import static br.com.vehicle.support.exception.AppCode.MSG_UPDATE_SUCCESS;
import static br.com.vehicle.support.exception.AppCode.VEHICLE;
import static br.com.vehicle.support.handler.JsonHandler.toJson;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Optional;

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
import br.com.vehicle.model.request.FetchRequest;
import br.com.vehicle.repository.VehicleRepository;
import br.com.vehicle.support.exception.FailCode;
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
	public void insertVehicleStatus201Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		String message = i18n.getMessage(MSG_INSERT_SUCCESS, Locale.getDefault(), vehicle.getModel());
		mvc.perform(post("/vehicle/api/insert")
				.content(toJson(vehicle)).contentType(contentType))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Assert.assertEquals(Boolean.TRUE, vehicleRepository.findOne(Example.of(vehicle)).isPresent());
	}
	
	@Test
	public void insertVehiclePriceNullStatus417Test() throws Exception
	{
		String message = i18n.getMessage("NotNull.Vehicle.price", Locale.getDefault());
		
		Vehicle vehicle = newVehicle();
		vehicle.setPrice(null);
		
		mvc.perform(post("/vehicle/api/insert")
				.content(toJson(vehicle)).contentType(contentType))
		.andDo(print())
		.andExpect(status().isExpectationFailed())
		.andExpect(content().contentTypeCompatibleWith(contentType))
		.andExpect(jsonPath("$[0].message", is(message)));
	}
	
	@Test
	public void insertVehicleSizeModelStatus417Test() throws Exception
	{
		String message = i18n.getMessage("Size.Vehicle.model", Locale.getDefault());
		
		Vehicle vehicle = newVehicle();
		vehicle.setModel("012345678901234567890123456789012345678901234568790");
		
		mvc.perform(post("/vehicle/api/insert")
				.content(toJson(vehicle)).contentType(contentType))
		.andDo(print())
		.andExpect(status().isExpectationFailed())
		.andExpect(content().contentTypeCompatibleWith(contentType))
		.andExpect(jsonPath("$[0].message", is(message)));
	}

	@Test
	public void updateVehicleStatus200Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		insertVehicle(vehicle);

		String message = i18n.getMessage(MSG_UPDATE_SUCCESS, Locale.getDefault(), vehicle.getModel());

		Integer year = 2017;
		vehicle.setYear(year);
		mvc.perform(put("/vehicle/api/update")
				.content(toJson(vehicle)).contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Optional<Vehicle> vehicleUpdated = vehicleRepository.findById(vehicle.getModel());
		Assert.assertEquals(Boolean.TRUE, vehicleUpdated.isPresent());
		Assert.assertEquals(year, vehicleUpdated.get().getYear());
	}

	@Test
	public void deleteVehicleStatus200Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		insertVehicle(vehicle);

		String message = i18n.getMessage(MSG_DELETE_SUCCESS, Locale.getDefault(), vehicle.getModel());

		mvc.perform(delete("/vehicle/api/delete")
				.param("model", vehicle.getModel()).contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Assert.assertEquals(Boolean.FALSE, vehicleRepository.findById(vehicle.getModel()).isPresent());
	}

	@Test
	public void deleteVehicleNullStatus417Test() throws Exception
	{
		String vehicleMsg = i18n.getMessage(VEHICLE, Locale.getDefault());
		String message = i18n.getMessage(FailCode.MSG_REQUIRED, Locale.getDefault(), vehicleMsg);
		
		String model = "";
		mvc.perform(delete("/vehicle/api/delete")
				.param("model", model).contentType(contentType))
				.andDo(print())
				.andExpect(status().isExpectationFailed())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$[0].message", is(message)));
	}

	@Test
	public void fetchOneVehicleStatus200Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		insertVehicle(vehicle);

		FetchRequest<Vehicle> request = new FetchRequest<Vehicle>();
		request.setExample(vehicle);
		request.setPage(0);
		request.setSize(1);

		mvc.perform(get("/vehicle/api/fetch")
				.content(toJson(request)).contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.results", hasSize(greaterThanOrEqualTo(1))))
				.andExpect(jsonPath("$.results[0].model", is(vehicle.getModel())));
	}

	@Test
	public void fetchAllVehicleStatus200Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		insertVehicle(vehicle);

		FetchRequest<Vehicle> request = new FetchRequest<Vehicle>();
		request.setPage(0);
		request.setSize(10);

		mvc.perform(get("/vehicle/api/fetch")
				.content(toJson(request)).contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.results", hasSize(greaterThanOrEqualTo(1))))
				.andExpect(jsonPath("$.results[0].model", is(vehicle.getModel())));
	}

	@Test
	public void fetchVehicleByTypeStatus200Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		insertVehicle(vehicle);

		mvc.perform(get("/vehicle/api/fetch/type/" + vehicle.getType() + "/0/1")
				.contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.results", hasSize(greaterThanOrEqualTo(1))))
				.andExpect(jsonPath("$.results[0].model", is(vehicle.getModel())));
	}
	
	@Test
	public void fetchVehicleByColorStatus200Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		insertVehicle(vehicle);
		
		mvc.perform(get("/vehicle/api/fetch/color/" + vehicle.getColor() + "/0/1")
				.contentType(contentType))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(contentType))
		.andExpect(jsonPath("$.results", hasSize(greaterThanOrEqualTo(1))))
		.andExpect(jsonPath("$.results[0].model", is(vehicle.getModel())));
	}
	
	@Test
	public void fetchVehicleByBrandStatus200Test() throws Exception
	{
		Vehicle vehicle = newVehicle();
		insertVehicle(vehicle);
		
		mvc.perform(get("/vehicle/api/fetch/brand/" + vehicle.getBrand() + "/0/1")
				.contentType(contentType))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(contentType))
		.andExpect(jsonPath("$.results", hasSize(greaterThanOrEqualTo(1))))
		.andExpect(jsonPath("$.results[0].model", is(vehicle.getModel())));
	}

	private void insertVehicle(Vehicle vehicle)
	{
		vehicleRepository.insert(vehicle);
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
