package com.carservice.carServiceDocker.repository;

import com.carservice.carServiceDocker.model.Car;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

    @Query("SELECT p FROM Car c WHERE c.car LIKE :model")
    Car getCarByModel(@Param("model") String model);

    @Query("SELECT p FROM Car c WHERE c.car LIKE :brand")
    Car getCarByBrand(@Param("brand") String brand);
}

