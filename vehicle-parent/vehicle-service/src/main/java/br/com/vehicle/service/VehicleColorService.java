package br.com.vehicle.service;

import java.util.List;

import br.com.vehicle.model.domain.VehicleColor;

public interface VehicleColorService
{
	VehicleColor insert(String color);

	void delete(String color);

	List<String> fetchAll();

	VehicleColor fetchOne(String color);
}
