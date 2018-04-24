package br.com.projetas.crud.car.repository;


import br.com.projetas.crud.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by joaol on 15/04/18.
 */
public interface CarRepositoryCustom {

    List<Car> findByFilter(String brand, String model, String color, Integer maxYear, Integer minYear, Float maxPrice, Float minPrice, Boolean isNew);


}
