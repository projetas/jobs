package br.com.projetas.carapp.carapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetas.carapp.carapp.entity.Car;
import br.com.projetas.carapp.carapp.repository.CarRepository;

@Service
public class CarService {

	@Autowired
    private CarRepository repository;
     
    public Iterable<Car> findAll() {
        return repository.findAll();
    }
     
    public Object findById(Long id) {
        return repository.findById(id);
    }
     
    public Car save(Car car) {
        return repository.save(car);
    }
     
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
	
}
