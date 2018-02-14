package br.com.diego.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.diego.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	@Query("from Vehicle where model like concat(?1, '%')")
	public List<Vehicle> findByParam(String model);
}
