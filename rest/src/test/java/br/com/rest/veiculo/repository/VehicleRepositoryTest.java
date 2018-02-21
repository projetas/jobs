package br.com.rest.veiculo.repository;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rest.veiculo.entity.Veiculo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {

 @Autowired
 private VeiculoRepository vehicleRepository;

 @Rule
 public ExpectedException thrown = ExpectedException.none();

 @Test
 public void createShouldNotSave() {
  Veiculo vehicle = new Veiculo("GOL", "Model 1", "black", 2018, 10000,
   "Description GOL", true);
  this.vehicleRepository.save(vehicle);
  Assertions.assertThat(vehicle.getCreateAt()).isNotEqualTo(null);
 }

 @Test
 public void createShouldNotUpdate() {
  Veiculo vehicle = new Veiculo("GOL", "Model 1", "black", 2018, 10000,
   "Description GOL", true);
  this.vehicleRepository.save(vehicle);
  Assertions.assertThat(vehicle.getUpdateAt()).isNotEqualTo(null);
 }

 @Test
 public void createShouldPersist() {
  Veiculo vehicle = new Veiculo("GOL", "Model 1", "black", 2018, 10000, "Description GOL", true);
  this.vehicleRepository.save(vehicle);
  Assertions.assertThat(vehicle.getId()).isNotNull();
  Assertions.assertThat(vehicle.getBrand()).isEqualTo("GOL");
  Assertions.assertThat(vehicle.getModel()).isEqualTo("Model 1");
  Assertions.assertThat(vehicle.getColor()).isEqualTo("black");
  Assertions.assertThat(vehicle.getYear()).isEqualTo(2018);
  Assertions.assertThat(vehicle.getPrice()).isEqualTo(10000);
  Assertions.assertThat(vehicle.getDescription()).isEqualTo("Description GOL");
  Assertions.assertThat(vehicle.isNew()).isEqualTo(true);
  Assertions.assertThat(vehicle.getCreateAt()).isNotNull();
  Assertions.assertThat(vehicle.getUpdateAt()).isNotNull();
 }

 @Test
 public void deleteShouldRemove() {
  Veiculo vehicle = new Veiculo("GOL", "Model 1", "black", 2018, 10000, "Description GOL", true);
  this.vehicleRepository.save(vehicle);
  vehicleRepository.delete(vehicle);
  Assertions.assertThat(vehicleRepository.findOne(vehicle.getId())).isNull();
 }

 @Test
 public void createWhenBrandIsNullShouldThrowConstraintViolationException() {
  thrown.expect(ConstraintViolationException.class);
  thrown.expectMessage("Required field brand");
  Veiculo vehicle = new Veiculo(null, "Model 1", "black", 2018, 10000,
   "Description GOL", true);
  this.vehicleRepository.save(vehicle);
 }

 @Test
 public void updateShouldChangeAndPersist() {
  Veiculo vehicle = new Veiculo("GOL", "Model 1", "black", 2018, 10000,
   "Description GOL", true);
  this.vehicleRepository.save(vehicle);
  vehicle.setBrand("GOL WOLKS");
  vehicle.setModel("Model X");
  vehicle.setColor("silver");
  vehicle.setPrice(100000);
  vehicle.setDescription("A brand new Model X");
  vehicle.setNew(false);
  this.vehicleRepository.save(vehicle);
  vehicle = this.vehicleRepository.findOne(vehicle.getId());
  Assertions.assertThat(vehicle.getBrand()).isEqualTo("GOL WOLKS");
  Assertions.assertThat(vehicle.getModel()).isEqualTo("Model X");
  Assertions.assertThat(vehicle.getColor()).isEqualTo("silver");
  Assertions.assertThat(vehicle.getPrice()).isEqualTo(100000);
  Assertions.assertThat(vehicle.getDescription()).isEqualTo("A brand new Model X");
  Assertions.assertThat(vehicle.isNew()).isEqualTo(false);
 }

 @Test
 public void createWhenModelIsNullShouldThrowConstraintViolationException() {
  thrown.expect(ConstraintViolationException.class);
  thrown.expectMessage("Required field model");
  Veiculo vehicle = new Veiculo("GOL", null, "black", 2018, 10000,
   "Description GOL", true);
  this.vehicleRepository.save(vehicle);
 }

}