package br.com.rest.veiculo.service;

import java.util.List;

import br.com.rest.veiculo.entity.Veiculo;

public interface VeiculoServiceInterface {

	List<Veiculo> getAllVehicle();
	Veiculo getVehicleByCod(Long cod);
	Veiculo addVehicle(Veiculo vehicle);
	void deleteVehicle(Long cod);
	Veiculo updateVehicle(Long cod, Veiculo vehicle);
	void validateVehicle(Long cod);

}
