package br.com.projetas.crud.car.service;

import br.com.projetas.crud.car.model.Car;

import java.util.List;

public interface CarService {

    Car save(Car car);

    Car findById(Long id);

    List<Car> findAll();

    List<Car> findByFilter(String brand, String model, String color, Integer maxYear, Integer minYear, Float maxPrice, Float minPrice, Boolean isNew);

    void delete (Long id);

    Car update (Car car) throws Exception;

}
