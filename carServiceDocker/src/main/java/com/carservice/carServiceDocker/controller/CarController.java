package com.carservice.carServiceDocker.controller;

import com.carservice.carServiceDocker.service.CarService;
import com.carservice.carServiceDocker.service.dto.CarDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> listCars() {
        return ResponseEntity.ok(this.carService.listAllCars());
    }

    @GetMapping("/test")
    public ResponseEntity<String> findAllTest() {
        return ResponseEntity.ok("Teste Ok!");
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> findCarById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.carService.getCarById(id));
    }

    @GetMapping("/car/{brand}")
    public ResponseEntity<CarDTO> findCarByBrand(@PathVariable("brand") String brand) {
        return ResponseEntity.ok(this.carService.getCarByBrand(brand));
    }

    @GetMapping("/car/{model}")
    public ResponseEntity<CarDTO> findCarByModel(@PathVariable("model") String model) {
        return ResponseEntity.ok(this.carService.getCarByModel(model));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity delete(@PathVariable("id") Long carId) {
        this.carService.delete(carId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/car")
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO carDTO) throws URISyntaxException {
        CarDTO car = this.carService.insert(carDTO);
        return ResponseEntity.created(new URI("api/car/" + car.getIdCar()))
                .body(car);
    }

    @PutMapping("/car")
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(this.carService.update(carDTO));
    }
}
