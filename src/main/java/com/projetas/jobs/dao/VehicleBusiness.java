package com.projetas.jobs.dao;

import com.projetas.jobs.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleBusiness extends JpaRepository<Vehicle,Long> {

}