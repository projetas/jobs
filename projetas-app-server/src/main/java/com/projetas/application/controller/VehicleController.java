package com.projetas.application.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetas.application.message.request.VehicleForm;
import com.projetas.application.message.response.ResponseMessage;
import com.projetas.application.model.Vehicle;
import com.projetas.application.repository.VehicleRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	@Autowired
	VehicleRepository vehicleRepository;

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Vehicle>> listAllVehicles() {
		List<Vehicle> vehicle = vehicleRepository.findAll();
		return ResponseEntity.ok(vehicle);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getVehicleById(@PathVariable("id") Long id) {
		return vehicleRepository.findById(id)
				.map(vehicle -> ResponseEntity.ok().body(vehicle))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> createVehicle(@Valid @RequestBody VehicleForm vehicleForm) {
		Vehicle savedVehicle = vehicleRepository.save(vehicleForm.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedVehicle.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateVehicle(@Valid @RequestBody VehicleForm vehicleForm, @PathVariable Long id) {
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
		if (!vehicleOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Vehicle vehicle = vehicleForm.to(vehicleOpt.get());
		vehicleRepository.save(vehicle);
		return ResponseEntity.ok(vehicle);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id) {
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
		if (!vehicleOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		vehicleRepository.deleteById(id);
		return ResponseEntity.ok(new ResponseMessage("Veiculo removido com sucesso!"));
	}

}
