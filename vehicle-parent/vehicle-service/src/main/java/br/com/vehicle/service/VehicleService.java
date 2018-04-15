package br.com.vehicle.service;

import br.com.vehicle.model.domain.Vehicle;
import br.com.vehicle.model.request.FetchRequest;
import br.com.vehicle.model.response.FetchResponse;

public interface VehicleService
{
	void insert(Vehicle vehicle);

	void update(Vehicle vehicle);

	void delete(String licensePlate);

	FetchResponse<Vehicle> fetchAll(FetchRequest<Vehicle> request);

	Vehicle fetchOne(String model);
}
