package br.com.vehicle.service.impl;

import static br.com.vehicle.support.exception.AppCode.VEHICLE_BRAND;
import static br.com.vehicle.support.exception.FailCode.MSG_ALREADY_EXIST;
import static br.com.vehicle.support.exception.FailCode.MSG_REQUIRED;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vehicle.model.domain.VehicleBrand;
import br.com.vehicle.repository.VehicleBrandRepository;
import br.com.vehicle.service.VehicleBrandService;
import static br.com.vehicle.support.handler.TextHandle.*;
import br.com.vehicle.support.validation.Check;

@Service
@Transactional
public class VehicleBrandServiceImpl implements VehicleBrandService
{
	@Autowired
	private VehicleBrandRepository brandRepository;

	@Override
	public VehicleBrand insert(String brand)
	{
		Check.isNotEmpty(brand, MSG_REQUIRED, VEHICLE_BRAND);
		Check.isFalse(brandRepository.findById(brand).isPresent(), MSG_ALREADY_EXIST, VEHICLE_BRAND, brand);
		return brandRepository.insert(VehicleBrand.builder().brand(cleanText(brand, true)).build());
	}

	@Override
	public void delete(String brand)
	{
		Check.isNotEmpty(brand, MSG_REQUIRED, VEHICLE_BRAND);
		brandRepository.deleteById(cleanText(brand, true));
	}

	@Override
	public List<String> fetchAll()
	{
		Optional<Collection<VehicleBrand>> brands = Optional.of(brandRepository.findAll());
		return brands.isPresent() ? brands.get().stream().map(VehicleBrand::getBrand).collect(Collectors.toList()) : null;
	}

	@Override
	public VehicleBrand fetchOne(String brand)
	{
		Check.isNotEmpty(brand, MSG_REQUIRED, VEHICLE_BRAND);
		Optional<VehicleBrand> one = brandRepository.findById(cleanText(brand, true));
		return one.isPresent() ? one.get() : null;
	}
}
