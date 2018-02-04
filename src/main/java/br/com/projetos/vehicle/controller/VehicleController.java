package br.com.projetos.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetos.vehicle.model.Vehicle;
import br.com.projetos.vehicle.service.VehicleService;


@Controller
@RequestMapping("/")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@GetMapping
	ModelAndView findAll() {
		ModelAndView searchVehicle = new ModelAndView("searchVehicle");
		searchVehicle.addObject("vehicles", vehicleService.findAll());
		return searchVehicle;
	}
	
	@PostMapping
	String save(@ModelAttribute Vehicle vehicle){
		vehicleService.saveAndFlush(vehicle);
		return "redirect:/";
	}
	
	@GetMapping("/update")
	public ModelAndView update(@Param("id") String id) {
		ModelAndView modelAndView = new ModelAndView("persistVehicle");
		modelAndView.addObject(vehicleService.findById(Long.valueOf(id)));
		return modelAndView;
		
	}
	
	@GetMapping("/create")
	public ModelAndView create(){
		ModelAndView modelAndView = new ModelAndView("persistVehicle");
		modelAndView.addObject(new Vehicle());
		return modelAndView;
	}
	
	@DeleteMapping("{id}")
	void remove(@PathVariable Long id) {
		vehicleService.deleteById(id);
	}

}
