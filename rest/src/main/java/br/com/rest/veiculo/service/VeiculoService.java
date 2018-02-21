package br.com.rest.veiculo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.Utils;
import br.com.rest.veiculo.entity.Veiculo;
import br.com.rest.veiculo.exceptions.VehicleNoteFoundException;
import br.com.rest.veiculo.repository.VeiculoRepository;
import br.com.rest.veiculo.utils.Util;

@Service
public class VeiculoService implements VeiculoServiceInterface {

	private final Logger logger = Logger.getLogger(VeiculoService.class);

	@Autowired
	VeiculoRepository repository;

	@Override
	public List<Veiculo> getAllVehicle() {
		logger.info("Get all vehicles");
		List<Veiculo> vehicles = new ArrayList<>();
		vehicles = (List<Veiculo>) repository.findAll();
		return vehicles;
	}

	@Override
	public Veiculo getVehicleByCod(Long cod) {
		logger.info("Get vehicle, by cod" + cod);
		validateVehicle(cod);
		return repository.findOne(cod);
	}

	@Override
	public Veiculo addVehicle(Veiculo vehicle) {
		logger.info("ADD vehicle");
		return repository.save(vehicle);
	}

	@Override
	public void deleteVehicle(Long cod) {
		logger.info("Deleting vehicle, by cod" + cod);
		validateVehicle(cod);
		repository.delete(cod);
	}

	@Override
	public Veiculo updateVehicle(Long cod, Veiculo vehicleUp) {

		//Validation of vehicle
		validateVehicle(cod);

		//Validating veiculo
		Util.validar(vehicleUp);

		//Find vehicle
		Veiculo vehicle = repository.findOne(cod);

		vehicle.setColor(vehicleUp.getColor());
		vehicle.setYear(vehicleUp.getYear());
		vehicle.setPrice(vehicleUp.getPrice());
		vehicle.setBrand(vehicleUp.getBrand());
		vehicle.setModel(vehicleUp.getModel());
		vehicle.setDescription(vehicleUp.getDescription());
		vehicle.setNew(vehicleUp.isNew());

		return repository.save(vehicle);
	}


	@Override
	public void validateVehicle(Long cod) {
		logger.info("Validating vehicle: " + cod);

		if (repository.findOne(cod) == null){
			logger.info("Vehicle NOT FOUND");
			throw new VehicleNoteFoundException("Vehicle NOT FOUND");
		}
	}
}
