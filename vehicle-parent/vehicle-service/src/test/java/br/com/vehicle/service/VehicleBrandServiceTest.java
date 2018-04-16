package br.com.vehicle.service;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.vehicle.model.domain.VehicleBrand;
import br.com.vehicle.repository.VehicleBrandRepository;
import br.com.vehicle.service.impl.VehicleBrandServiceImpl;
import br.com.vehicle.support.exception.VehicleException;
import br.com.vehicle.support.validation.BeanValidator;

@RunWith(SpringRunner.class)
public class VehicleBrandServiceTest
{
	@TestConfiguration
	static class VehicleBrandServiceTestContextConfig
	{
		@Bean
		public VehicleBrandService brandService()
		{
			return new VehicleBrandServiceImpl();
		}
	}

	@Autowired
	private VehicleBrandService brandService;

	@MockBean
	private VehicleBrandRepository brandRepository;

	@MockBean
	private BeanValidator validator;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void insertBrandSucessTest()
	{
		brandService.insert("FORD");
	}

	@Test
	public void insertBrandExceptionTest()
	{
		VehicleBrand brand = newBrandVehicle();
		thrown.expect(VehicleException.class);
		Mockito.when(brandRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(brand));
		brandService.insert(brand.getBrand());
	}

	@Test
	public void deleteBrandSucessTest()
	{
		VehicleBrand brand = newBrandVehicle();
		Mockito.when(brandRepository.findOne(ArgumentMatchers.any())).thenReturn(Optional.of(brand));
		brandService.delete(brand.getBrand());
	}

	private VehicleBrand newBrandVehicle()
	{
		return VehicleBrand.builder().brand("FORD").build();
	}
}
