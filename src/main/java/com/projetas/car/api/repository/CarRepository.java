package com.projetas.car.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetas.car.api.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

	public Page<Car> findAll(Pageable pageable);
}
