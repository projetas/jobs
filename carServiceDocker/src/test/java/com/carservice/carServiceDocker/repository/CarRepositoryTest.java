package com.carservice.carServiceDocker.repository;

import com.carservice.carServiceDocker.model.Car;
import com.carservice.carServiceDocker.service.dto.CarDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    private CarDTO carDTO;

    @Autowired
    TestEntityManager testEntityManager;

    @Before
    public void setUp() throws Exception {
        Car car = new Car();
        car.setBrand("Chevrolet");
        car.setModel("Corsa");
        car.setColor("Branco");
        car.setYear(1995);
        car.setPrice(9700.50);
        car.setDescription("Carro usado");
        car.setIsNew(false);
        car.setRegisterDate(LocalDate.now());
        car.setChangeDate(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void deleteNullId() throws Exception {
        carRepository.delete(new CarDTO());
    }

    @Test(expected = ConstraintViolationException.class)
    public void insertNull() throws Exception {
        carRepository.save(new CarDTO());
    }

    @Test
    public void findByModel() throws Exception {
        testEntityManager.persist(carDTO);
        testEntityManager.flush();

        Car carByModel = carRepository.getCarByModel(carDTO.getModel());
        assertThat(carByModel.getModel()).isEqualTo(carDTO.getModel());
    }

    @Test
    public void insertCar() throws Exception {
        carDTO.setDescription("InsertTest2");
        carRepository.saveAndFlush(carDTO);

        Car insertedPoi = carRepository.getCarByModel(carDTO.getDescription());

        assertThat(insertedPoi).isEqualTo(carDTO);
    }

}
