package br.com.vehicle.api.rest;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.vehicle.api.VehicleApplication;
import br.com.vehicle.model.domain.VehicleColor;
import br.com.vehicle.repository.VehicleColorRepository;
import br.com.vehicle.support.exception.AppCode;
import br.com.vehicle.support.exception.FailCode;
import br.com.vehicle.support.handler.I18nHandler;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = VehicleApplication.class)
@AutoConfigureMockMvc
@EnableMongoRepositories(basePackages = { "br.com.vehicle.repository" })
@TestPropertySource(locations = "classpath:application-integration.properties")
public class VehicleColorRestApiIntegrationTest
{
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mvc;

	@Autowired
	private I18nHandler i18n;

	@Autowired
	private VehicleColorRepository colorRepository;

	@Before
	public void setup()
	{
		colorRepository.deleteAll();
	}

	@Test
	public void insertColorStatus201Test() throws Exception
	{
		String color = "HONDA";
		String message = i18n.getMessage(AppCode.MSG_INSERT_SUCCESS, Locale.getDefault(), color);

		mvc.perform(post("/vehicle/color/api/insert")
				.param("color", color).contentType(contentType))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Assert.assertEquals(Boolean.TRUE, colorRepository.findById(color).isPresent());
	}

	@Test
	public void insertColorNullStatus417Test() throws Exception
	{
		String colorMsg = i18n.getMessage(AppCode.VEHICLE_COLOR, Locale.getDefault());
		String message = i18n.getMessage(FailCode.MSG_REQUIRED, Locale.getDefault(), colorMsg);

		String color = "";
		mvc.perform(post("/vehicle/color/api/insert")
				.param("color", color).contentType(contentType))
				.andDo(print())
				.andExpect(status().isExpectationFailed())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$[0].message", is(message)));
	}

	@Test
	public void deleteColorStatus200Test() throws Exception
	{
		String color = "FIAT";
		String message = i18n.getMessage(AppCode.MSG_DELETE_SUCCESS, Locale.getDefault(), color);

		insertColor(color);
		mvc.perform(delete("/vehicle/color/api/delete")
				.param("color", color).contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Assert.assertEquals(Boolean.FALSE, colorRepository.findById(color).isPresent());
	}

	@Test
	public void deleteColorNullStatus417Test() throws Exception
	{
		String colorMsg = i18n.getMessage(AppCode.VEHICLE_COLOR, Locale.getDefault());
		String message = i18n.getMessage(FailCode.MSG_REQUIRED, Locale.getDefault(), colorMsg);

		String color = "";
		insertColor(color);
		mvc.perform(delete("/vehicle/color/api/delete")
				.param("color", color).contentType(contentType))
				.andDo(print())
				.andExpect(status().isExpectationFailed())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$[0].message", is(message)));
	}

	@Test
	public void fetchColorStatus200Test() throws Exception
	{
		String color = "FORD";
		insertColor(color);
		mvc.perform(get("/vehicle/color/api/fetch").contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.results", hasSize(greaterThanOrEqualTo(1))))
				.andExpect(jsonPath("$.results[0]", is(color)));
	}

	@Test
	public void fetchColorNotFoundTest() throws Exception
	{
		mvc.perform(get("/vehicle/color/api/fetch").contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.results", hasSize(0)));
	}

	private void insertColor(String color)
	{
		colorRepository.insert(VehicleColor.builder().color(color).build());
	}
}
