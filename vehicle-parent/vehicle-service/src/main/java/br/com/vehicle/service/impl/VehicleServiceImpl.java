package br.com.vehicle.service.impl;

import static br.com.vehicle.support.exception.AppCode.VEHICLE;
import static br.com.vehicle.support.exception.AppCode.VEHICLE_BRAND;
import static br.com.vehicle.support.exception.AppCode.VEHICLE_COLOR;
import static br.com.vehicle.support.exception.FailCode.MSG_ALREADY_EXIST;
import static br.com.vehicle.support.exception.FailCode.MSG_NOT_FOUND;
import static br.com.vehicle.support.exception.FailCode.MSG_REQUIRED;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vehicle.model.domain.Vehicle;
import br.com.vehicle.model.domain.VehicleBrand;
import br.com.vehicle.model.domain.VehicleColor;
import br.com.vehicle.model.request.FetchRequest;
import br.com.vehicle.model.response.FetchResponse;
import br.com.vehicle.repository.VehicleRepository;
import br.com.vehicle.service.VehicleBrandService;
import br.com.vehicle.service.VehicleColorService;
import br.com.vehicle.service.VehicleService;
import br.com.vehicle.support.validation.BeanValidator;
import br.com.vehicle.support.validation.Check;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService
{
	@Autowired
	private VehicleBrandService brandService;
	
	@Autowired
	private VehicleColorService colorService;

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private BeanValidator validator;
	
	@Override
	public void insert(Vehicle vehicle)
	{
		Check.isNotNull(vehicle, MSG_REQUIRED, VEHICLE);
		validator.validate(vehicle);
		
		String model = vehicle.getModel();
		Example<Vehicle> example = Example.of(new Vehicle(model));
		Check.isFalse(vehicleRepository.findOne(example).isPresent(), MSG_ALREADY_EXIST, VEHICLE, model);

		vehicle.setBrand(checkBrand(vehicle.getBrand()));
		vehicle.setColor(checkColor(vehicle.getColor()));
		vehicle.setRegisterDate(LocalTime.now());
		vehicleRepository.insert(vehicle);
	}

	@Override
	public void update(Vehicle vehicle)
	{
		Check.isNotNull(vehicle, MSG_REQUIRED, VEHICLE);
		validator.validate(vehicle);

		String model = vehicle.getModel();
		Example<Vehicle> example = Example.of(new Vehicle(model));
		Check.isTrue(vehicleRepository.findOne(example).isPresent(), MSG_NOT_FOUND, model);

		vehicle.setBrand(checkBrand(vehicle.getBrand()));
		vehicle.setColor(checkColor(vehicle.getColor()));
		vehicle.setModifiedDate(LocalTime.now());
		vehicleRepository.save(vehicle);
	}

	@Override
	public void delete(String model)
	{
		Check.isNotEmpty(model, MSG_REQUIRED, VEHICLE);

		Example<Vehicle> example = Example.of(new Vehicle(model));
		Optional<Vehicle> vehicle = vehicleRepository.findOne(example);
		Check.isTrue(vehicle.isPresent(), MSG_NOT_FOUND, model);

		vehicleRepository.deleteById(vehicle.get().getModel());
	}

	@Override
	public FetchResponse<Vehicle> fetchAll(FetchRequest<Vehicle> request)
	{
		PageRequest requestPage = PageRequest.of(request.getPage(), request.getSize());
		Example<Vehicle> example = request.getExample() == null ? null : Example.of(request.getExample());
		Page<Vehicle> page = example == null ? vehicleRepository.findAll(requestPage) : vehicleRepository.findAll(example, requestPage);
		
		Check.isTrue(page.hasContent(), MSG_NOT_FOUND, VEHICLE);
		return FetchResponse.<Vehicle>builder()
					.results(page.getContent())
					.page(request.getPage())
					.totalElements(page.getNumberOfElements())
					.totalPages(page.getTotalPages())
					.build();
	}

	@Override
	public Vehicle fetchOne(String model)
	{
		Check.isNotEmpty(model, MSG_REQUIRED, VEHICLE);

		Example<Vehicle> example = Example.of(new Vehicle(model));
		Optional<Vehicle> vehicle = vehicleRepository.findOne(example);
		Check.isTrue(vehicle.isPresent(), MSG_NOT_FOUND, model);
		
		return vehicle.get();
	}
	
	private VehicleBrand checkBrand(String inputBrand)
	{
		Check.isNotEmpty(inputBrand, MSG_REQUIRED, VEHICLE_BRAND);
		VehicleBrand brand = brandService.fetchOne(inputBrand);
		return brand != null ? brand :  brandService.insert(inputBrand);
	}
	
	private VehicleColor checkColor(String inputColor)
	{
		Check.isNotEmpty(inputColor, MSG_REQUIRED, VEHICLE_COLOR);
		VehicleColor color = colorService.fetchOne(inputColor);
		return color != null ? color :  colorService.insert(inputColor);
	}
}
