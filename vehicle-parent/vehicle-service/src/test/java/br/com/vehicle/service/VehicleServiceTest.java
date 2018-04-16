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

import br.com.vehicle.model.domain.Vehicle;
import br.com.vehicle.model.domain.VehicleBrand;
import br.com.vehicle.model.domain.VehicleColor;
import br.com.vehicle.model.domain.VehicleType;
import br.com.vehicle.repository.VehicleRepository;
import br.com.vehicle.service.impl.VehicleServiceImpl;
import br.com.vehicle.support.exception.VehicleException;
import br.com.vehicle.support.validation.BeanValidator;

@RunWith(SpringRunner.class)
public class VehicleServiceTest
{
	@TestConfiguration
	static class VehicleServiceTestContextConfig
	{
		@Bean
		public VehicleService vehicleService()
		{
			return new VehicleServiceImpl();
		}
	}
	
	@Autowired
	private VehicleService vehicleService;

	@MockBean
	private VehicleRepository vehicleRepository;

	@MockBean
	private VehicleBrandService brandService;

	@MockBean
	private VehicleColorService colorService;
	
	@MockBean
	private BeanValidator validator;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void insertVehicleSucessTest()
	{
		vehicleService.insert(newVehicle());
	}
	
	@Test
	public void insertVehicleExceptionTest()
	{
		thrown.expect(VehicleException.class);
		Mockito.when(vehicleRepository.findOne(ArgumentMatchers.any())).thenReturn(Optional.of(newVehicle()));
		vehicleService.insert(newVehicle());
	}
	
	@Test
	public void updateVehicleSucessTest()
	{
		Mockito.when(vehicleRepository.findOne(ArgumentMatchers.any())).thenReturn(Optional.of(newVehicle()));
		vehicleService.update(newVehicle());
	}
	
	@Test
	public void updateVehicleExceptionTest()
	{
		thrown.expect(VehicleException.class);
		vehicleService.update(newVehicle());
	}
	
	@Test
	public void deleteVehicleSucessTest()
	{
		Mockito.when(vehicleRepository.findOne(ArgumentMatchers.any())).thenReturn(Optional.of(newVehicle()));
		vehicleService.delete(newVehicle().getModel());
	}
	
	@Test
	public void deleteVehicleExceptionTest()
	{
		thrown.expect(VehicleException.class);
		vehicleService.delete(newVehicle().getModel());
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
