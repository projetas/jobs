package com.projetas.car.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetas.car.api.event.NewResourceEvent;
import com.projetas.car.api.model.Car;
import com.projetas.car.api.repository.CarRepository;
import com.projetas.car.api.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarResource {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarService carService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Car> listAllCars() {
		return carRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Car> createCar(@Valid @RequestBody Car car, HttpServletResponse response) {
		Car carSaved = carRepository.save(car);
		publisher.publishEvent(new NewResourceEvent(this, response, carSaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(carSaved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> searchByCode(@PathVariable Long id) {
		 Car car = carRepository.findOne(id);
		 return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void dropCar(@PathVariable Long id) {
		carRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable Long id, @Valid @RequestBody Car car) {
		Car carSaved = carService.updateCar(id, car);
		return ResponseEntity.ok(carSaved);
	}
}
