package br.com.rest.veiculo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.veiculo.entity.Veiculo;
import br.com.rest.veiculo.service.VeiculoService;

@RestController
public class VeiculoController {

	private final VeiculoService vehicleService;

	@Autowired
	public VeiculoController(VeiculoService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@GetMapping(path = "/user/vehicles")
	public ResponseEntity<?> getAllVehicle() {
		List<Veiculo> vehicles = new ArrayList<>();
		vehicles = vehicleService.getAllVehicle();
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}

	@GetMapping(path = "/user/vehicles/{cod}")
	public ResponseEntity<?> getVehicleByCod(@PathVariable("cod") Long cod) {
		Veiculo vehicle = vehicleService.getVehicleByCod(cod);
		return new ResponseEntity<>(vehicle, HttpStatus.OK);
	}


	@PostMapping(path = "/admin/vehicles")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> addVehicle(@Valid @RequestBody Veiculo vehicle) {
		return new ResponseEntity<>(vehicleService.addVehicle(vehicle), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/admin/vehicles/{cod}")
	public ResponseEntity<?> deleteVehicle(@PathVariable Long cod) {
		vehicleService.deleteVehicle(cod);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/admin/vehicles/{cod}")
	public ResponseEntity<?> updateVehicle(@PathVariable Long cod, @Valid @RequestBody Veiculo vehicle) {
		return new ResponseEntity<>(vehicleService.updateVehicle(cod, vehicle), HttpStatus.OK);
	}

}
