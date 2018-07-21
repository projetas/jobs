package com.carservice.carServiceDocker.controller;

import com.carservice.carServiceDocker.repository.CarRepository;
import com.carservice.carServiceDocker.service.CarService;
import com.carservice.carServiceDocker.service.dto.CarDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.http.MediaType;

public class CarControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private CarService service;

    @Autowired
    private CarRepository poiRepository;

    private CarDTO carDTO;

    @Before
    public void setUp() {
        carDTO = new CarDTO();
        carDTO.setBrand("Chevrolet");
        carDTO.setModel("Corsa");
        carDTO.setColor("Branco");
        carDTO.setYear(1995);
        carDTO.setPrice(9700.50);
        carDTO.setDescription("Carro usado");
        carDTO.setIsNew(false);
        carDTO.setRegisterDate(LocalDate.now());
        carDTO.setChangeDate(null);
    }

    @Test
    public void listAllCarsAPI() throws Exception {

        List<CarDTO> CarDTOList = Arrays.asList(carDTO);

        given(service.listAllCars()).willReturn(CarDTOList);

        mock.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[*].model").value(hasItem(carDTO.getModel())));
    }

    @Test
    public void nonExistingUrl() throws Exception {
        mock.perform(get("/api/non-existing")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void saveTest() throws Exception {
        given(service.insert(carDTO)).willReturn(carDTO);

        mock.perform(post("/api/pois")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(carDTO)))
                .andExpect(status().isCreated());
    }

    public static byte[] convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }
}
