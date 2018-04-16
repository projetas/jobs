package br.com.projetas.carapp.carapp.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.projetas.carapp.carapp.entity.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

}
