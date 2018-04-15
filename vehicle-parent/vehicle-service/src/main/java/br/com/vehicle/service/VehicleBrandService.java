package br.com.vehicle.service;

import java.util.List;

import br.com.vehicle.model.domain.VehicleBrand;

public interface VehicleBrandService
{
	VehicleBrand insert(String brand);

	void delete(String brand);

	List<String> fetchAll();
	
	VehicleBrand fetchOne(String brand);
}
