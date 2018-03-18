package com.projetas.car.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Car> listAllCars(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

	@GetMapping("/{cod}")
	public ResponseEntity<Car> SearchByCode(@PathVariable Long cod) {
		 Car car = carRepository.findOne(cod);
		 return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Car> createCar(@Valid @RequestBody Car car, HttpServletResponse response) {
		Car carSaved = carRepository.save(car);
		publisher.publishEvent(new NewResourceEvent(this, response, carSaved.getCod()));
		return ResponseEntity.status(HttpStatus.CREATED).body(carSaved);
	}

	@DeleteMapping("/{cod}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void dropCar(@PathVariable Long cod) {
		carRepository.delete(cod);
	}

	@PutMapping("/{cod}")
	public ResponseEntity<Car> updateCar(@PathVariable Long cod, @Valid @RequestBody Car car) {
		Car carSaved = carService.updateCar(cod, car);
		return ResponseEntity.ok(carSaved);
	}

	@PutMapping("/{cod}/newcar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long cod, @RequestBody Boolean newCar) {
		carService.updateNewCarProperty(cod, newCar);
	}
}
