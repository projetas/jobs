package com.projetas.jobs.controller;

import com.projetas.jobs.util.ResponseJson;
import com.projetas.jobs.dao.VehicleBusiness;
import com.projetas.jobs.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    @Autowired VehicleBusiness vehicleBusiness;

    @RequestMapping(path = "vehicle/all",method = RequestMethod.GET, produces = "application/json")
    public List<Vehicle> vehicle(){
        return vehicleBusiness.findAll();
    }

    @RequestMapping(path = "vehicle",method = RequestMethod.POST)
    public  ResponseEntity<ResponseJson<Vehicle>> newVeicle(@RequestBody @Valid Vehicle vehicle, BindingResult binding){
        ResponseJson<Vehicle> response = new ResponseJson<>();
        if (binding.hasErrors()){
            binding.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        vehicleBusiness.save(vehicle);
        response.setData(vehicle);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "vehicle/{id}",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponseJson<Vehicle>> getVeicle(@PathVariable("id") Long id){
        ResponseJson<Vehicle> response = new ResponseJson<>();
        Vehicle vehicle = vehicleBusiness.findOne(id);
        if(vehicle == null)
            response.getErros().add("Vehicle not found");
        else response.setData(vehicle);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "vehicle",method = RequestMethod.PUT)
    public ResponseEntity updateVehicle(@RequestBody Vehicle vehicle){
        vehicleBusiness.save(vehicle);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @RequestMapping(path = "vehicle/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteVehicle(@PathVariable("id") Long id){
        vehicleBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
