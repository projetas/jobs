package br.com.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.vehicle.model.domain.VehicleBrand;

@Repository
public interface VehicleBrandRepository extends MongoRepository<VehicleBrand, String>
{

}
