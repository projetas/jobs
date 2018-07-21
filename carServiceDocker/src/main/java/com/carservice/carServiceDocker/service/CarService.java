package com.carservice.carServiceDocker.service;

import com.carservice.carServiceDocker.service.dto.CarDTO;

import java.util.List;

public interface CarService {

    public List<CarDTO> listAllCars();

    public CarDTO insert(CarDTO carDTO);

    public CarDTO update(CarDTO carDTO);

    public void delete(Long carId);

    public CarDTO getCarById(Long carId);

    public CarDTO getCarByModel(String model);

    public CarDTO getCarByBrand(String brand);
}
