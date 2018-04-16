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

import br.com.vehicle.model.domain.VehicleColor;
import br.com.vehicle.repository.VehicleColorRepository;
import br.com.vehicle.service.impl.VehicleColorServiceImpl;
import br.com.vehicle.support.exception.VehicleException;
import br.com.vehicle.support.validation.BeanValidator;

@RunWith(SpringRunner.class)
public class VehicleColorServiceTest
{
	@TestConfiguration
	static class VehicleColorServiceTestContextConfig
	{
		@Bean
		public VehicleColorService colorService()
		{
			return new VehicleColorServiceImpl();
		}
	}

	@Autowired
	private VehicleColorService colorService;

	@MockBean
	private VehicleColorRepository colorRepository;

	@MockBean
	private BeanValidator validator;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void insertColorSucessTest()
	{
		colorService.insert("FORD");
	}

	@Test
	public void insertColorExceptionTest()
	{
		VehicleColor color = newColorVehicle();
		thrown.expect(VehicleException.class);
		Mockito.when(colorRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(color));
		colorService.insert(color.getColor());
	}

	@Test
	public void deleteColorSucessTest()
	{
		VehicleColor color = newColorVehicle();
		Mockito.when(colorRepository.findOne(ArgumentMatchers.any())).thenReturn(Optional.of(color));
		colorService.delete(color.getColor());
	}

	private VehicleColor newColorVehicle()
	{
		return VehicleColor.builder().color("BRANCA").build();
	}
}
