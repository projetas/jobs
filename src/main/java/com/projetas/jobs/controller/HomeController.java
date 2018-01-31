package com.projetas.jobs.controller;

import com.projetas.jobs.dao.VehicleBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    VehicleBusiness vehicleBusiness;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView home(){
        return new ModelAndView("home");
    }
}