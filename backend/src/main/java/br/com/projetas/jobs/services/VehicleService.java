package br.com.projetas.jobs.services;

import br.com.projetas.jobs.exceptions.BadRequestException;
import br.com.projetas.jobs.exceptions.NotFoundException;
import br.com.projetas.jobs.model.Vehicle;
import br.com.projetas.jobs.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleService {

    private VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Iterable<Vehicle> list() {
        return repository.findAll();
    }

    public void create(Vehicle vehicle) {
        repository.save(vehicle);
    }

    public void update(Vehicle vehicle, Long id) {
        Vehicle persistentVehicle = repository.findById(id).orElseThrow(BadRequestException::new);
        persistentVehicle.setColor(vehicle.getColor());
        persistentVehicle.setPrice(vehicle.getPrice());
        persistentVehicle.setDescription(vehicle.getDescription());
        persistentVehicle.setUpdate(LocalDateTime.now());

        repository.save(persistentVehicle);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Vehicle findById(long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}
