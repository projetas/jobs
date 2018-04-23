package br.com.projetas.crud.car.controller;


import br.com.projetas.crud.car.model.Car;
import br.com.projetas.crud.car.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by joaol on 15/04/18.
 */
@Controller
@RequestMapping("/car")
public class CarController {

    private static final Logger logger = LogManager.getLogger(CarController.class);

    @Autowired
    private CarService carService;


    @GetMapping
    @ResponseBody
    public List<Car> listar() {

        return carService.findAll();

    }

    @GetMapping("/{id}")
    public @ResponseBody
    Car findById(@PathVariable(value = "id") Long id) {

        return this.carService.findById(id);
    }


    @PostMapping
    public String salvar(Car car) {

        logger.info("Saving " + car);
        this.carService.save(car);
        return "redirect:/";
    }


    @DeleteMapping("/{id}")
    public @ResponseBody
    String delete(@PathVariable(value = "id") Long id) {

        this.carService.delete(id);
        return "redirect:/";
    }

    @PutMapping
    public @ResponseBody
    String update(Car car) throws Exception {

        logger.info("Updating " + car);
        this.carService.update(car);
        return "redirect:/";
    }
}
