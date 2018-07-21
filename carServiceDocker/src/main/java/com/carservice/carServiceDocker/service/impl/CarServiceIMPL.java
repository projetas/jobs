package com.carservice.carServiceDocker.service.impl;

import com.carservice.carServiceDocker.model.Car;
import com.carservice.carServiceDocker.repository.CarRepository;
import com.carservice.carServiceDocker.service.CarService;
import com.carservice.carServiceDocker.service.dto.CarDTO;
import com.carservice.carServiceDocker.service.mapper.CarMapper;

import java.util.List;

public class CarServiceIMPL implements CarService {

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    public CarServiceIMPL(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarDTO insert(CarDTO carDTO) {
        if (carDTO.getIdCar() != null) {
            return this.update(carDTO);
        }
        Car car = this.carRepository.save(carMapper.carDTOToCar(carDTO));
        return carMapper.carToCarDTO(car);
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        if (carDTO.getIdCar() == null) {
            return this.insert(carDTO);
        }
        Car car = this.carRepository.save(carMapper.carDTOToCar(carDTO));
        return carMapper.carToCarDTO(car);
    }

    @Override
    public void delete(Long carId) {
        this.carRepository.deleteById(carId);
    }

    @Override
    public CarDTO getCarById(Long carId) {
        return carMapper.carToCarDTO(this.carRepository.findById(carId).orElse(null));
    }

    @Override
    public CarDTO getCarByModel(String model) {
        return carMapper.carToCarDTO(this.carRepository.getCarByBrand(model));
    }

    @Override
    public CarDTO getCarByBrand(String brand) {
        return carMapper.carToCarDTO(this.carRepository.getCarByBrand(brand));
    }

    @Override
    public List<CarDTO> listAllCars() {
        return carMapper.carToCarDTO(this.carRepository.findAll());
    }
}
