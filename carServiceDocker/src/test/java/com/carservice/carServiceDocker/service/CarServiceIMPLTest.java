package com.carservice.carServiceDocker.service;

import com.carservice.carServiceDocker.model.Car;
import com.carservice.carServiceDocker.repository.CarRepository;
import com.carservice.carServiceDocker.service.impl.CarServiceIMPL;
import com.carservice.carServiceDocker.service.mapper.CarMapper;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarServiceIMPLTest {

    @TestConfiguration
    static class PoiServiceImplTextConfig {
        @Autowired
        private CarMapper poiMapper;

        @Autowired
        private CarRepository poiRepository;

        @Bean
        public CarService carService() {
            return new CarServiceIMPL(poiRepository, poiMapper);
        }
    }

    @Autowired
    private CarMapper poiMapper;

    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private CarService carService;

    private Car car;

    @Before
    public void setUp() {
        car = new Car();
        car.setBrand("Chevrolet");
        car.setModel("Corsa");
        car.setColor("Branco");
        car.setYear(1995);
        car.setPrice(9700.50);
        car.setDescription("Carro usado");
        car.setIsNew(false);
        car.setRegisterDate(LocalDate.now());
        car.setChangeDate(null);

        carRepository.saveAndFlush(car);

        Mockito.when(carRepository.getCarByModel(car.getModel())).thenReturn(car);

        List<Car> carList = new ArrayList<>();
        carList.add(car);
        car.setBrand("Renault");
        car.setModel("Sandero");
        car.setColor("Prata");
        car.setYear(2010);
        car.setPrice(14000.50);
        car.setDescription("Carro usado");
        car.setIsNew(false);
        car.setRegisterDate(LocalDate.now());
        car.setChangeDate(null);
        carList.add(car);
        Mockito.when(carRepository.findAll).thenReturn(carList);

        Mockito.when(carRepository.save(car)).thenReturn(car);
    }

}
