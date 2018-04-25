package br.com.projetas.jobs.services;

import br.com.projetas.jobs.exceptions.BadRequestException;
import br.com.projetas.jobs.exceptions.NotFoundException;
import br.com.projetas.jobs.model.Vehicle;
import br.com.projetas.jobs.model.VehicleMock;
import br.com.projetas.jobs.repositories.VehicleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class VehicleServiceTest {

    private static final long ANY_ID = 1L;
    private static final long INVALID_ID = 1111L;
    private static final Vehicle FERRARI_250 = VehicleMock.ferrari250();

    @Mock
    private VehicleRepository repository;
    @InjectMocks
    private VehicleService service;

    @Before
    public void setUp() {
        when(repository.findById(ANY_ID)).thenReturn(Optional.of(FERRARI_250));
    }

    @Test(expected = NotFoundException.class)
    public void findByIdWithInvalidId_shouldThrowException() {
        service.findById(INVALID_ID);
    }

    @Test
    public void findById_shouldCallRepositoryFindById() {
        service.findById(ANY_ID);

        verify(repository).findById(ANY_ID);
    }

    @Test
    public void listVehicles_shouldCallRepositoryList() {
        service.list();

        verify(repository).findAll();
    }

    @Test
    public void createVehicle_shouldCallRepositorySave() {
        Vehicle vehicle = VehicleMock.vwFusca();
        service.create(vehicle);
    }

    @Test
    public void createVehicle_shouldFillCreationDateField() {
        Vehicle vehicle = VehicleMock.vwFusca();
        service.create(vehicle);

        Assert.assertNotNull("creation date field should not be null", vehicle.getCreation());
    }

    @Test
    public void updateVehicle_shouldCallRepositoryUpdate() {
        Vehicle persistentVehicle = VehicleMock.vwFusca();
        when(repository.findById(ANY_ID)).thenReturn(Optional.of(persistentVehicle));

        service.update(VehicleMock.vwFusca(), ANY_ID);

        verify(repository).save(persistentVehicle);
    }

    @Test(expected = BadRequestException.class)
    public void updateVehicleWithInvalidId_shouldThrowException() {
        service.update(VehicleMock.vwFusca(), INVALID_ID);
    }

    @Test
    public void updateVehicle_shouldNotUpdateBrand() {
        Vehicle messedUpFerrari = VehicleMock.mock("Fiat", "250", "Red", 1962, BigDecimal.valueOf(350000), false);

        service.update(messedUpFerrari, ANY_ID);

        verify(repository).save(FERRARI_250);
    }

    @Test
    public void updateVehicle_shouldNotUpdateModel() {
        Vehicle messedUpFerrari = VehicleMock.mock("Ferrari", "Fusca", "Red", 1962, BigDecimal.valueOf(350000), false);

        service.update(messedUpFerrari, ANY_ID);

        verify(repository).save(FERRARI_250);
    }

    @Test
    public void updateVehicle_shouldNotUpdateYear() {
        Vehicle messedUpFerrari = VehicleMock.mock("Ferrari", "250", "Red", 2000, BigDecimal.valueOf(350000), false);

        service.update(messedUpFerrari, ANY_ID);

        verify(repository).save(FERRARI_250);
    }

    @Test
    public void updateVehicle_shouldUpdateColor() {
        Vehicle changedFerrari = VehicleMock.ferrari250();
        changedFerrari.setColor("Black");

        service.update(changedFerrari, ANY_ID);

        verify(repository).save(changedFerrari);
    }

    @Test
    public void updateVehicle_shouldUpdatePrice() {
        Vehicle changedFerrari = VehicleMock.ferrari250();
        changedFerrari.setPrice(BigDecimal.valueOf(200000));

        service.update(changedFerrari, ANY_ID);

        verify(repository).save(changedFerrari);
    }

    @Test
    public void updateVehicle_shouldUpdateDescription() {
        Vehicle changedFerrari = VehicleMock.ferrari250();
        changedFerrari.setDescription("New comment");

        service.update(changedFerrari, ANY_ID);

        verify(repository).save(changedFerrari);
    }

    @Test
    public void updateVehicle_shouldFillUpdateDateField() {
        Vehicle vehicle = VehicleMock.vwFusca();
        service.update(vehicle, ANY_ID);

        Assert.assertNotNull("update date field should not be null", vehicle.getCreation());
    }

    @Test
    public void deleteVehicle_shouldCallRepositoryDelete() {
        service.delete(ANY_ID);
        verify(repository).deleteById(ANY_ID);
    }

}