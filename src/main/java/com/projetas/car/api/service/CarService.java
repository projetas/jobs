package com.projetas.car.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetas.car.api.model.Car;
import com.projetas.car.api.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	public Car updateCar(Long id, Car car) {
		Car carSaved = searchByCode(id);

		BeanUtils.copyProperties(car, carSaved, "id");
		return carRepository.save(carSaved);
	}

	private Car searchByCode(Long id) {
		Car carSaved = carRepository.findOne(id);
		if (carSaved == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return carSaved;
	}
}
