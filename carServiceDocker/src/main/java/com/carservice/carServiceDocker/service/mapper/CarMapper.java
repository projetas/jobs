package com.carservice.carServiceDocker.service.mapper;

import com.carservice.carServiceDocker.model.Car;
import com.carservice.carServiceDocker.service.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO carToCarDTO(Car car);

    Car carDTOToCar(CarDTO carDTO);

    List<CarDTO> carToCarDTO(List<Car> all);
}
