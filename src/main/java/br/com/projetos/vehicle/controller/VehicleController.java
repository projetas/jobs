package br.com.projetos.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		return getPersistModelView(vehicleService.findById(Long.valueOf(id)));
		
	}
	
	@GetMapping("/create")
	public ModelAndView create(){
		return getPersistModelView(new Vehicle());
	}
	
	@GetMapping("/delete")
	String remove(@Param("id") Long id) {
		vehicleService.deleteById(id);
		return "redirect:/";
	}
	
	private ModelAndView getPersistModelView(Vehicle vehicle){
		ModelAndView modelAndView = new ModelAndView("persistVehicle");
		modelAndView.addObject(vehicle);
		
		return modelAndView;
	}

}
