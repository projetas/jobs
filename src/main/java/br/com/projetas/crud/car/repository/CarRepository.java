package br.com.projetas.crud.car.repository;


import br.com.projetas.crud.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by joaol on 15/04/18.
 */
public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryCustom {
}
