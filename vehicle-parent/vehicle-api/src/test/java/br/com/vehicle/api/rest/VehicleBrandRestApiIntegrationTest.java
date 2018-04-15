package br.com.vehicle.api.rest;

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
import br.com.vehicle.model.domain.VehicleBrand;
import br.com.vehicle.repository.VehicleBrandRepository;
import br.com.vehicle.support.exception.AppCode;
import br.com.vehicle.support.exception.FailCode;
import br.com.vehicle.support.handler.I18nHandler;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = VehicleApplication.class)
@AutoConfigureMockMvc
@EnableMongoRepositories(basePackages = { "br.com.vehicle.repository" })
@TestPropertySource(locations = "classpath:application-integration.properties")
public class VehicleBrandRestApiIntegrationTest
{
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private I18nHandler i18n;

	@Autowired
	private VehicleBrandRepository brandRepository;

	@Before
	public void setup()
	{
		brandRepository.deleteAll();
	}

	@Test
	public void insertBrandStatus201Test() throws Exception
	{
		String brand = "HONDA";
		String message = i18n.getMessage(AppCode.MSG_INSERT_SUCCESS, Locale.getDefault(), brand);
		
		mvc.perform(post("/vehicle/brand/api/insert")
				.param("brand", brand).contentType(contentType))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Assert.assertEquals(Boolean.TRUE, brandRepository.findById(brand).isPresent());
	}

	@Test
	public void insertBrandNullStatus417Test() throws Exception
	{
		String brandMsg = i18n.getMessage(AppCode.VEHICLE_BRAND, Locale.getDefault());
		String message = i18n.getMessage(FailCode.MSG_REQUIRED, Locale.getDefault(), brandMsg);
		
		String brand = "";
		mvc.perform(post("/vehicle/brand/api/insert")
				.param("brand", brand).contentType(contentType))
				.andDo(print())
				.andExpect(status().isExpectationFailed())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$[0].message", is(message)));
	}

	@Test
	public void deleteBrandStatus200Test() throws Exception
	{
		String brand = "FIAT";
		String message = i18n.getMessage(AppCode.MSG_DELETE_SUCCESS, Locale.getDefault(), brand);
		
		insertBrand(brand);
		mvc.perform(delete("/vehicle/brand/api/delete")
				.param("brand", brand).contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.message", is(message)));
		Assert.assertEquals(Boolean.FALSE, brandRepository.findById(brand).isPresent());
	}
	
	@Test
	public void deleteBrandNullStatus417Test() throws Exception
	{
		String brandMsg = i18n.getMessage(AppCode.VEHICLE_BRAND, Locale.getDefault());
		String message = i18n.getMessage(FailCode.MSG_REQUIRED, Locale.getDefault(), brandMsg);
		
		String brand = "";
		insertBrand(brand);
		mvc.perform(delete("/vehicle/brand/api/delete")
				.param("brand", brand).contentType(contentType))
				.andDo(print())
				.andExpect(status().isExpectationFailed())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$[0].message", is(message)));
	}

	@Test
	public void fetchBrandStatus200Test() throws Exception
	{
		String brand = "FORD";
		insertBrand(brand);
		mvc.perform(get("/vehicle/brand/api/fetch").contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.results", hasSize(greaterThanOrEqualTo(1))))
				.andExpect(jsonPath("$.results[0]", is(brand)));
	}

	@Test
	public void fetchBrandNotFoundTest() throws Exception
	{
		mvc.perform(get("/vehicle/brand/api/fetch").contentType(contentType))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(contentType))
				.andExpect(jsonPath("$.results", hasSize(0)));
	}

	private void insertBrand(String brand)
	{
		brandRepository.insert(VehicleBrand.builder().brand(brand).build());
	}
}
