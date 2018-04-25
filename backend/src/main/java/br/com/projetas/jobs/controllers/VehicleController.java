package br.com.projetas.jobs.controllers;

import br.com.projetas.jobs.model.Vehicle;
import br.com.projetas.jobs.services.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    private VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @RequestMapping(path = "/vehicles", method = RequestMethod.GET)
    public Iterable<Vehicle> list() {
        return service.list();
    }

    @RequestMapping(path = "/vehicle", method = RequestMethod.POST)
    public void create(@RequestBody Vehicle vehicle) {
        service.create(vehicle);
    }

    @RequestMapping(path = "/vehicle/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Vehicle vehicle, @PathVariable Long id) {
        service.update(vehicle, id);
    }

    @RequestMapping(path = "/vehicle/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/vehicle/{id}", method = RequestMethod.GET)
    public Vehicle findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
