package br.com.projetas.carapp.carapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetas.carapp.carapp.entity.Car;
import br.com.projetas.carapp.carapp.repository.CarRepository;

@Controller
public class CarController {

	@Autowired
	private CarRepository service;

	@GetMapping("/")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("/car");
		mv.addObject("cars", service.findAll());

		return mv;
	}

	@GetMapping("/add")
	public ModelAndView add(Object car) {

		ModelAndView mv = new ModelAndView("/carAdd");
		
		mv.addObject("car", new Car());
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {

		Object car = service.findById(id);
		return editCar(car);
	}

	public ModelAndView editCar(Object car) {

		ModelAndView mv = new ModelAndView("/carEdit");
		
		mv.addObject("car", car);
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		service.deleteById(id);
		return findAll();
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Car car, BindingResult result) {

		if (result.hasErrors()) {
			return add(car);
		}

		service.save(car);
		return findAll();
	}

}
