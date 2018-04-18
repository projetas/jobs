package br.com.projetas.crud.car.service;

import br.com.projetas.crud.car.model.Car;

import java.util.List;

public interface CarService {

    Car save(Car car);

    Car findById(Long id);

    List<Car> findAll();

    void delete (Long id);

    Car update (Car car, Long id) throws Exception;

}
