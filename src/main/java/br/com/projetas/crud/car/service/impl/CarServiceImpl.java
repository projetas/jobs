package br.com.projetas.crud.car.service.impl;

import br.com.projetas.crud.car.model.Car;
import br.com.projetas.crud.car.repository.CarRepository;
import br.com.projetas.crud.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {


    @Autowired
    private CarRepository carRepository;


    public Car save(Car car) {

        Assert.notNull(car, "Car cannot be null.");
        Assert.hasText(car.getBrand(), "brand cannot be empty.");
        Assert.hasText(car.getModel(), "model cannot be empty.");
        Assert.hasText(car.getColor(), "color cannot be empty.");
        Assert.notNull(car.getYear(), "year cannot be null.");
        Assert.notNull(car.getPrice(), "Price cannot be null.");
        Assert.notNull(car.getIsNew(), "isNew cannot be null.");

        car.setCreatedDate(new Date());
        car.setUpdatedDate(null);

        return carRepository.save(car);

    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "id cannot be null.");
        carRepository.delete(id);
    }

    @Override
    @Transactional( readOnly = false, rollbackFor = Exception.class )
    public Car update(Car car) throws Exception {

        Assert.notNull(car, "Car cannot be null.");
        Assert.notNull(car.getId(), "id cannot be null.");
        Assert.hasText(car.getBrand(), "brand cannot be empty.");
        Assert.hasText(car.getModel(), "model cannot be empty.");
        Assert.hasText(car.getColor(), "color cannot be empty.");
        Assert.notNull(car.getYear(), "year cannot be null.");
        Assert.notNull(car.getPrice(), "Price cannot be null.");
        Assert.notNull(car.getIsNew(), "isNew cannot be null.");

        Car oldCar = carRepository.getOne(car.getId());

        if (oldCar == null) {
            throw new Exception("Not exist car with this Id=" + car.getId());
        }

        oldCar.setBrand(car.getBrand());
        oldCar.setModel(car.getModel());
        oldCar.setColor(car.getColor());
        oldCar.setYear(car.getYear());
        oldCar.setPrice(car.getPrice());
        oldCar.setDescription(car.getDescription());
        oldCar.setIsNew(car.getIsNew());
        oldCar.setUpdatedDate(new Date());

        car = carRepository.save(oldCar);

        return car;

    }


}
