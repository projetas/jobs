package br.com.projetas.vehiclerestwebapp.repository;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.projetas.vehiclerestwebapp.model.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
// if executing with a external database
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VehicleRepositoryTest {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistData() {
		Vehicle vehicle = new Vehicle("Tesla", "Model 3", "black", 2018, 35000, 
								"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
		Assertions.assertThat(vehicle.getId()).isNotNull();
		Assertions.assertThat(vehicle.getBrand()).isEqualTo("Tesla");
		Assertions.assertThat(vehicle.getModel()).isEqualTo("Model 3");
		Assertions.assertThat(vehicle.getColor()).isEqualTo("black");
		Assertions.assertThat(vehicle.getYear()).isEqualTo(2018);
		Assertions.assertThat(vehicle.getPrice()).isEqualTo(35000);
		Assertions.assertThat(vehicle.getDescription()).isEqualTo("A brand new Model 3");
		Assertions.assertThat(vehicle.isNew()).isEqualTo(true);
		Assertions.assertThat(vehicle.getUpdateTimestamp()).isNotNull();
		Assertions.assertThat(vehicle.getRegisterTimestamp()).isNotNull();
	}
	
	@Test
	public void deleteShouldRemoveData() {
		Vehicle vehicle = new Vehicle("Tesla", "Model 3", "black", 2018, 35000, 
				"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
		vehicleRepository.delete(vehicle);
		Assertions.assertThat(vehicleRepository.findOne(vehicle.getId())).isNull();
	}
	
	@Test
	public void updateShouldChangeAndPersistData() {
		Vehicle vehicle = new Vehicle("Tesla", "Model 3", "black", 2018, 35000, 
				"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
		vehicle.setBrand("Tesla Motors");
		vehicle.setModel("Model X");
		vehicle.setColor("silver");
		vehicle.setPrice(100000);
		vehicle.setDescription("A brand new Model X");
		vehicle.setNew(false);
		this.vehicleRepository.save(vehicle);
		vehicle = this.vehicleRepository.findOne(vehicle.getId());
		Assertions.assertThat(vehicle.getBrand()).isEqualTo("Tesla Motors");
		Assertions.assertThat(vehicle.getModel()).isEqualTo("Model X");
		Assertions.assertThat(vehicle.getColor()).isEqualTo("silver");
		Assertions.assertThat(vehicle.getPrice()).isEqualTo(100000);
		Assertions.assertThat(vehicle.getDescription()).isEqualTo("A brand new Model X");
		Assertions.assertThat(vehicle.isNew()).isEqualTo(false);
	}
	
	@Test
	public void createWhenBrandIsNullShouldThrowConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Brand can not be null");
		Vehicle vehicle = new Vehicle(null, "Model 3", "black", 2018, 35000, 
				"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
	}
	
	@Test
	public void createWhenModelIsNullShouldThrowConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Model can not be null");
		Vehicle vehicle = new Vehicle("Tesla", null, "black", 2018, 35000, 
				"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
	}
	
	@Test
	public void createYearLessThanZeroShouldThrowConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Year must be a positive number");
		Vehicle vehicle = new Vehicle("Tesla", "Model 3", "black", -1, 35000, 
				"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
	}
	
	@Test
	public void createBrandGreaterThanMaxSizeShouldThrowConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Brand name length can not be greater than 40");
		Vehicle vehicle = new Vehicle("TeslaTeslaTeslaTeslaTeslaTeslaTeslaTeslaTeslaTesla", 
				"Model 3", "black", 2018, 35000, "A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
	}
	
	@Test
	public void createShouldNotSaveRegisterTimestampNull() {
		Vehicle vehicle = new Vehicle("Tesla", "Model 3", "black", 2018, 35000, 
				"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
		Assertions.assertThat(vehicle.getRegisterTimestamp()).isNotEqualTo(null);
	}
	
	@Test
	public void createShouldNotSaveUpdateTimestampNull() {
		Vehicle vehicle = new Vehicle("Tesla", "Model 3", "black", 2018, 35000, 
				"A brand new Model 3", true);
		this.vehicleRepository.save(vehicle);
		Assertions.assertThat(vehicle.getUpdateTimestamp()).isNotEqualTo(null);
	}
}
