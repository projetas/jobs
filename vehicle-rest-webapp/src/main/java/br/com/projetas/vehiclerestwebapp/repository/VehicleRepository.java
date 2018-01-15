package br.com.projetas.vehiclerestwebapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projetas.vehiclerestwebapp.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
