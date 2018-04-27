package br.com.projetas.jobs.controllers;

import br.com.projetas.jobs.model.Vehicle;
import br.com.projetas.jobs.model.VehicleMock;
import br.com.projetas.jobs.services.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VehicleControllerTest {

    private static final Long ANY_ID = 1L;

    @Mock
    private VehicleService service;
    @InjectMocks
    private VehicleController controller;

    @Test
    public void findById_shouldCallServiceFindById() {
        controller.findById(ANY_ID);

        Mockito.verify(service).findById(ANY_ID);
    }

    @Test
    public void list_shouldCallServiceList() {
        controller.list();

        Mockito.verify(service).list();
    }

    @Test
    public void create_shouldCallServiceCreate() {
        Vehicle vehicle = VehicleMock.vwFusca();
        controller.create(vehicle);

        Mockito.verify(service).create(vehicle);
    }

    @Test
    public void update_shouldCallServiceUpdate() {
        Vehicle vehicle = VehicleMock.vwFusca();
        controller.update(vehicle, ANY_ID);

        Mockito.verify(service).update(vehicle, ANY_ID);
    }

    @Test
    public void delete_shouldCallServiceDelete() {
        controller.delete(ANY_ID);

        Mockito.verify(service).delete(ANY_ID);
    }
}