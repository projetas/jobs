package br.com.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vehicle.model.domain.VehicleColor;

public interface VehicleColorRepository extends MongoRepository<VehicleColor, String>
{

}