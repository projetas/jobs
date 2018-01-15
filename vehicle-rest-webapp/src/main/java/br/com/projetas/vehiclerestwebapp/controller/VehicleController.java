package br.com.projetas.vehiclerestwebapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetas.vehiclerestwebapp.error.ResourceNotFoundException;
import br.com.projetas.vehiclerestwebapp.model.Vehicle;
import br.com.projetas.vehiclerestwebapp.repository.VehicleRepository;;

@RestController
public class VehicleController {

	private final VehicleRepository vehicleRepository;

	@Autowired
	public VehicleController(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	@GetMapping(path = "/user/vehicles") 
	public ResponseEntity<?> getAllVehicle() {
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findAll().forEach(vehicles::add);
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}
	
	@GetMapping(path = "/user/vehicles/{id}") 
	public ResponseEntity<?> getVehicleById(@PathVariable("id") Long id) {
		verifyIfVehicleExists(id);
		Vehicle vehicle = vehicleRepository.findOne(id);
		return new ResponseEntity<>(vehicle, HttpStatus.OK);
	}
	
	@PostMapping(path = "/admin/vehicles") 
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> addVehicle(@Valid @RequestBody Vehicle vehicle) {
		return new ResponseEntity<>(vehicleRepository.save(vehicle), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/admin/vehicles/{id}") 
	public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
		verifyIfVehicleExists(id);
		vehicleRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path = "/admin/vehicles/{id}") 
	public ResponseEntity<?> updateVehicle(@PathVariable Long id, @Valid @RequestBody Vehicle vehicle) {
		verifyIfVehicleExists(id);
		
		Vehicle vehicleUpdate = vehicleRepository.findOne(id);
		vehicleUpdate.setBrand(vehicle.getBrand());
		vehicleUpdate.setModel(vehicle.getModel());
		vehicleUpdate.setColor(vehicle.getColor());
		vehicleUpdate.setYear(vehicle.getYear());
		vehicleUpdate.setPrice(vehicle.getPrice());
		vehicleUpdate.setDescription(vehicle.getDescription());
		vehicleUpdate.setNew(vehicle.isNew());
		
		Vehicle vehicleUpdated = vehicleRepository.save(vehicleUpdate);
		return new ResponseEntity<>(vehicleUpdated, HttpStatus.OK);
	}
	
	private void verifyIfVehicleExists(Long id) {
		if (vehicleRepository.findOne(id) == null) 
			throw new ResourceNotFoundException("Vehicle not found for ID: " + id);
	}
	
}
