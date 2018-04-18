package br.com.projetas.crud.car.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by joaol on 15/04/18.
 */
@Controller
public class HomeController {

    @RequestMapping( value = { "/", "/login", "/cars" } )
    public String index() {

        return "index.html";
    }
}
