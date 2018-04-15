package br.com.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vehicle.model.domain.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String>
{

}
