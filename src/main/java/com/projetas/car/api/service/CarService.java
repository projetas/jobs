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

	public Car updateCar(Long cod, Car car) {
		Car carSaved = searchByCode(cod);

		BeanUtils.copyProperties(car, carSaved, "cod");
		return carRepository.save(carSaved);
	}

	private Car searchByCode(Long cod) {
		Car carSaved = carRepository.findOne(cod);
		if (carSaved == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return carSaved;
	}

	public void updateNewCarProperty(Long cod, Boolean newCar) {
		Car carSaved = searchByCode(cod);
		carSaved.setNewCar(newCar);
		carRepository.save(carSaved);
	}
}
