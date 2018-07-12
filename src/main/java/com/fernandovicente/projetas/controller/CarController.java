package com.fernandovicente.projetas.controller;

import com.fernandovicente.projetas.exception.ResourceNotFoundException;
import com.fernandovicente.projetas.model.Car;
import com.fernandovicente.projetas.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(maxAge = 3600)
public class CarController {

    @Autowired
    CarRepository carRepository;

    /**
     * Get all cars
     *
     * @return
     */
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Create a new car
     *
     * @param car
     * @return
     */
    @PostMapping("/car")
    public Car createCar(@Valid @RequestBody Car car) {
        car.setCreateDate(new Date());
        return carRepository.save(car);
    }

    /**
     * Retrieve a Car information by id
     *
     * @param carId
     * @return
     */
    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable(value = "id") Long carId) {
        return carRepository.findById(carId).
                orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
    }

    /**
     * Update car informations by id
     *
     * @param carId
     * @param carDetails
     * @return
     */
    @PutMapping("/car/{id}")
    public Car updateCar(@PathVariable(value = "id") Long carId,
                         @Valid @RequestBody Car carDetails) {

        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));

        car.setModel(carDetails.getModel());
        car.setBrand(carDetails.getBrand());
        car.setColor(carDetails.getColor());
        car.setPrice(carDetails.getPrice());
        car.setYear(carDetails.getYear());
        car.setNew(carDetails.isNew());
        car.setDescription(carDetails.getDescription());
        car.setLastUpdate(new Date());

        carRepository.save(car);

        return car;
    }

    /**
     * Delete a car by id
     *
     * @param carId
     * @return
     */
    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable(value = "id") Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        carRepository.delete(car);
        return ResponseEntity.ok().build();
    }


}
